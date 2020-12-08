package com.codurance.allaboard.core.acceptance;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.web.controllers.learningpath.LearningPathController;
import com.codurance.allaboard.web.views.LearningPathView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveLearningPathFeature {

  @Mock
  private LearningPaths learningPaths;

  @InjectMocks
  private FetchAllLearningPaths fetchAllLearningPaths;

  @InjectMocks
  private SaveLearningPath saveLearningPath;

  private LearningPathController learningPathController;


  @BeforeEach
  void setUp() {
    fetchAllLearningPaths = new FetchAllLearningPaths(learningPaths);
    learningPathController = new LearningPathController(fetchAllLearningPaths, saveLearningPath);
  }

  @Test
  void save_a_learningpath() {
    learningPathController.createLearningPath(new LearningPathView());
    verify(learningPaths, atLeastOnce()).save(any());
  }
}
