package com.codurance.allaboard.core.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.web.controllers.learningpath.LearningPathController;
import com.codurance.allaboard.web.views.LearningPathDetailView;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FetchLearningPathByIdFeature {

  @Mock
  LearningPaths learningPaths;
  private FetchAllLearningPaths fetchAllLearningPaths;
  private LearningPathController learningPathController;
  private SaveLearningPath saveLearningPath;
  private FetchLearningPathById fetchLearningPathById;

  @BeforeEach
  void setUp() {
    fetchAllLearningPaths = new FetchAllLearningPaths(learningPaths);
    saveLearningPath = new SaveLearningPath(learningPaths);
    fetchLearningPathById = new FetchLearningPathById(learningPaths);
    learningPathController =
        new LearningPathController(fetchAllLearningPaths, saveLearningPath, fetchLearningPathById);
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
    LearningPath learningPath = new LearningPath(id, "some title", "some description");

    given(learningPaths.findById(id)).willReturn(Optional.of(learningPath));

    ResponseEntity<LearningPathDetailView> response = learningPathController.getById(id);

    LearningPathDetailView learningPathDetailView = response.getBody();

    assertThat(response.getStatusCodeValue(), is(200));
    assertThat(learningPathDetailView.getId(), is(learningPath.getId()));
    assertThat(learningPathDetailView.getName(), is(learningPath.getName()));
    assertThat(learningPathDetailView.getDescription(), is(learningPath.getDescription()));
    verify(learningPaths, atLeastOnce()).findById(id);
  }
}
