package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LearningPathView implements Serializable {

  private long id;
  @NotEmpty(message = "Cannot be null or empty")
  private String name;
  @NotEmpty(message = "Cannot be null or empty")
  @Size(max = 1500, message = "Cannot be bigger than 1500 characters")
  private String description;

  public LearningPathView() {
  }

  private LearningPathView(long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public static LearningPathView from(LearningPath learningPath) {
    return new LearningPathView(
        learningPath.getId(),
        learningPath.getName(),
        learningPath.getDescription());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public long getId() {
    return id;
  }
}
