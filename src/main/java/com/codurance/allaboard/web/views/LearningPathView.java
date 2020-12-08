package com.codurance.allaboard.web.views;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LearningPathView implements Serializable {

  @NotEmpty(message = "Cannot be null or empty")
  private String name;
  @NotEmpty(message = "Cannot be null or empty")
  @Size(max = 1500, message = "Cannot be bigger than 1500 characters")
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
