package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Subtopic;

public class SubtopicView {

  private final long id;
  private final String name;

  private SubtopicView(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static SubtopicView from(Subtopic subtopic) {
    return new SubtopicView(subtopic.getId(), subtopic.getName());
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
