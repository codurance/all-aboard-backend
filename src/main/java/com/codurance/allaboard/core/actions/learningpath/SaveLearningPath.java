package com.codurance.allaboard.core.actions.learningpath;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveLearningPath {

  private final LearningPaths learningPaths;

  @Autowired
  public SaveLearningPath(LearningPaths learningPaths) {
    this.learningPaths = learningPaths;
  }

  public void save(LearningPath learningPath) {
    learningPaths.save(learningPath);
  }
}
