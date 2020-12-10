package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Topic;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LearningPathDetailView implements Serializable {

  private long id;
  @NotEmpty(message = "Cannot be null or empty")
  private String name;
  @NotEmpty(message = "Cannot be null or empty")
  @Size(max = 1500, message = "Cannot be bigger than 1500 characters")
  private String description;
  private Set<Topic> topics;

  public LearningPathDetailView(long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public LearningPathDetailView() {
  }

  public LearningPathDetailView(Long id, String name, String description, Set<Topic> topics) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.topics = topics;
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

  public Set<Topic> getTopics() {
    return topics;
  }
}
