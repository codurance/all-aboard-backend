package com.codurance.allaboard.core.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.web.controllers.learningpath.LearningPathController;
import com.codurance.allaboard.web.views.LearningPathView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@Disabled
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
    learningPathController = new LearningPathController(
        fetchAllLearningPaths,
        saveLearningPath,
        fetchLearningPathById
    );
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_learning_path() {
    ResponseEntity<LearningPathView> response = learningPathController.getById(1);

    assertThat(response.getStatusCode(), is(404));
  }
}
