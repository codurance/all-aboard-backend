package com.codurance.allaboard.core.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveLearningPathShould {

  @InjectMocks
  private SaveLearningPath saveLearningPath;
  @Mock
  private LearningPaths learningPaths;

  @Test
  void call_the_repository_save_method() {
    saveLearningPath.save(new LearningPath());
    verify(learningPaths, atLeastOnce()).save(any());
  }
}
