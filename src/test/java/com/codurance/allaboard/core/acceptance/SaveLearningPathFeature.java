package com.codurance.allaboard.core.acceptance;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveLearningPathFeature {

  private String name;
  private String description;
  @Mock
  private LearningPaths learningPaths;
  @InjectMocks
  private SaveLearningPath saveLearningPath;
  private LearningPath learningPath;

  @BeforeEach
  void setUp() {
    name = "stub name";
    description = "stub description";
    learningPath = new LearningPath(name, description);
  }

  @Test
  void save_a_learningpath() {
    saveLearningPath.save(learningPath);
    verify(learningPaths, atLeastOnce()).save(learningPath);
  }
}
