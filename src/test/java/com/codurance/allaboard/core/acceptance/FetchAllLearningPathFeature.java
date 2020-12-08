package com.codurance.allaboard.core.acceptance;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.web.controllers.learningpath.LearningPathController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FetchAllLearningPathFeature {

  @Mock
  private LearningPaths learningPaths;

  private FetchAllLearningPaths fetchAllLearningPaths;

  @InjectMocks
  private SaveLearningPath saveLearningPath;

  private LearningPathController learningPathController;


  @BeforeEach
  void setUp() {
    saveLearningPath = new SaveLearningPath(learningPaths);
    fetchAllLearningPaths = new FetchAllLearningPaths(learningPaths);
    learningPathController = new LearningPathController(fetchAllLearningPaths, saveLearningPath);
  }

  @Test
  void get_all_learningpath() {
    learningPathController.provideCatalog();
    verify(learningPaths, atLeastOnce()).findAll();
  }

}
