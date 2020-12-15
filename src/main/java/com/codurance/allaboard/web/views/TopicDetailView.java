package com.codurance.allaboard.web.views;

import static java.util.stream.Collectors.toList;

import com.codurance.allaboard.core.model.topic.Topic;

import java.util.List;

public class TopicDetailView {

  private final String name;
  private final List<SubtopicDetailView> subtopics;

  private TopicDetailView(String name, List<SubtopicDetailView> subtopicViews) {
    this.name = name;
    this.subtopics = subtopicViews;
  }

  public static TopicDetailView from(Topic topic) {
    return new TopicDetailView(
        topic.getName(),
        buildSubtopicViews(topic)
    );
  }

  private static List<SubtopicDetailView> buildSubtopicViews(Topic topic) {
    return topic.getSubtopics()
        .stream()
        .map(SubtopicDetailView::from)
        .collect(toList());
  }

  public String getName() {
    return name;
  }

  public List<SubtopicDetailView> getSubtopics() {
    return subtopics;
  }
}
