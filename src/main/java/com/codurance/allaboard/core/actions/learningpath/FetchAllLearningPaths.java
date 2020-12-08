package com.codurance.allaboard.core.actions.learningpath;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchAllLearningPaths {

  private final LearningPaths learningPaths;

  @Autowired
  public FetchAllLearningPaths(LearningPaths learningPaths) {
    this.learningPaths = learningPaths;
  }

  public List<LearningPath> getAll() {
    return (List<LearningPath>) learningPaths.findAll();
  }
}
