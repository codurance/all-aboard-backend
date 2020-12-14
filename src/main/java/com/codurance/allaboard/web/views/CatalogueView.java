package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogueView implements Serializable {

  private final List<LearningPathView> learningPaths;

  public CatalogueView(List<LearningPath> learningPaths) {
    this.learningPaths =
        learningPaths.stream()
            .map(
                learningPath ->
                    new LearningPathView(
                        learningPath.getId(),
                        learningPath.getName(),
                        learningPath.getDescription()))
            .collect(Collectors.toList());
  }

  public List<LearningPathView> getLearningPaths() {
    return learningPaths;
  }
}
