package com.codurance.allaboard.core.actions.learningpath;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;

public class SaveLearningPath {

  private final LearningPaths learningPaths;

  public SaveLearningPath(LearningPaths learningPaths) {
    this.learningPaths = learningPaths;
  }

  public void save(LearningPath learningPath) {
    learningPaths.save(learningPath);
  }
}
