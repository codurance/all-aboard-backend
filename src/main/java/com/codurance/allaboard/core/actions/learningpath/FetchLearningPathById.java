package com.codurance.allaboard.core.actions.learningpath;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchLearningPathById {

  private final LearningPaths learningPaths;

  @Autowired
  public FetchLearningPathById(LearningPaths learningPaths) {
    this.learningPaths = learningPaths;
  }

  public LearningPath findById(Long id) {
    Optional<LearningPath> optionalLearningPath = learningPaths.findById(id);

    if (optionalLearningPath.isEmpty()) {
      return null;
    }
    return optionalLearningPath.get();
  }
}
