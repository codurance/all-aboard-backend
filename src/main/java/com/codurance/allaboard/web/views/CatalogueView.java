package com.codurance.allaboard.web.views;

import static java.util.stream.Collectors.toList;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import java.util.List;

public class CatalogueView implements Serializable {

  private final List<LearningPathView> learningPaths;

  private CatalogueView(List<LearningPathView> learningPaths) {
    this.learningPaths = learningPaths;
  }

  public static CatalogueView from(List<LearningPath> learningPaths) {
    return new CatalogueView(learningPaths.stream()
        .map(LearningPathView::from)
        .collect(toList()));
  }

  public List<LearningPathView> getLearningPaths() {
    return learningPaths;
  }
}
