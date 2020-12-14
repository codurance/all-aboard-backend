package com.codurance.allaboard.web.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.web.controllers.learningpath.LearningPathController;
import com.codurance.allaboard.web.views.LearningPathDetailView;
import java.util.Optional;
import java.util.Set;

import com.codurance.allaboard.web.views.LearningPathView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class LearningPathControllerShould {

  @Mock
  LearningPaths learningPaths;

  private LearningPathController learningPathController;

  @BeforeEach
  void setUp() {
    learningPathController = new LearningPathController(
            new FetchAllLearningPaths(learningPaths),
            new SaveLearningPath(learningPaths),
            new FetchLearningPathById(learningPaths)
    );
  }

  @Test
  void save_a_learningpath() {
    learningPathController.createLearningPath(new LearningPathView());
    verify(learningPaths, atLeastOnce()).save(any());
  }

  @Test
  void get_all_learningPaths() {
    learningPathController.provideCatalog();
    verify(learningPaths, atLeastOnce()).findAll();
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_learning_path() {
    long id = 1;
    ResponseEntity<LearningPathDetailView> response = learningPathController.getById(id);

    assertThat(response.getStatusCodeValue(), is(404));
    verify(learningPaths, atLeastOnce()).findById(id);
  }

  @Test
  void answers_with_learning_path_if_asked_for_an_existent_one() {
    long id = 1;
    Topic topic = new Topic(1L, "topic name", "topic description");
    LearningPath learningPath = new LearningPath(id, "some title", "some description", Set.of(
        topic));

    given(learningPaths.findById(id))
        .willReturn(Optional.of(learningPath));

    ResponseEntity<LearningPathDetailView> response = learningPathController.getById(id);

    LearningPathDetailView learningPathDetailView = response.getBody();

    assertThat(response.getStatusCodeValue(), is(200));
    assertThat(learningPathDetailView.getId(), is(learningPath.getId()));
    assertThat(learningPathDetailView.getName(), is(learningPath.getName()));
    assertThat(learningPathDetailView.getDescription(), is(learningPath.getDescription()));
    assertThat(learningPathDetailView.getTopics(), contains(topic));
    verify(learningPaths, atLeastOnce()).findById(id);
  }
}
