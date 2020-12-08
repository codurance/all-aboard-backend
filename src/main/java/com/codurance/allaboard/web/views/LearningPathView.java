package com.codurance.allaboard.web.views;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class LearningPathView implements Serializable {

  @NotNull
  private String name;
  @NotNull
  private String description;

  public LearningPathView(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public LearningPathView() {
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
