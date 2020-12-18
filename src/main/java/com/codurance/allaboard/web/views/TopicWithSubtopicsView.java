package com.codurance.allaboard.web.views;

import java.io.Serializable;
import java.util.List;

public class TopicWithSubtopicsView extends TopicView implements Serializable {

  private List<SubtopicDetailView> subtopics;

  public TopicWithSubtopicsView(List<SubtopicDetailView> subtopics) {
    this.subtopics = subtopics;
  }

  public TopicWithSubtopicsView(String name, String description,
      List<SubtopicDetailView> subtopics) {
    super(name, description);
    this.subtopics = subtopics;
  }


  public TopicWithSubtopicsView() {
  }

  public List<SubtopicDetailView> getSubtopics() {
    return subtopics;
  }

  public void setSubtopics(List<SubtopicDetailView> subtopics) {
    this.subtopics = subtopics;
  }
}
