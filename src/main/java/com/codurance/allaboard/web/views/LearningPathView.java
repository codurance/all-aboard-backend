package com.codurance.allaboard.web.views;

import java.io.Serializable;

public class LearningPathView implements Serializable {

  private final String name;
  private final String description;

  public LearningPathView(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
