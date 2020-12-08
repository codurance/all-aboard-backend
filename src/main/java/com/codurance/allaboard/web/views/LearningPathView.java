package com.codurance.allaboard.web.views;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class LearningPathView implements Serializable {

  @NotEmpty(message = "Cannot be null or empty")
  private String name;
  @NotEmpty(message = "Cannot be null or empty")
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
