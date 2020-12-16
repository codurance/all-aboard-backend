package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import java.util.stream.Collectors;

public class TopicService {

  private Topics topics;

  public TopicService(Topics topics) {
    this.topics = topics;
  }

  public Topic storeTopicWithSubtopics(
      TopicWithSubtopicsView topicWithSubtopicsView) {

    Topic topic = new Topic(topicWithSubtopicsView.getName(),
        topicWithSubtopicsView.getDescription());

    List<SubtopicDetailView> subtopicsDetailView = topicWithSubtopicsView.getSubtopics();
    List<Subtopic> subtopics = subtopicsDetailView.stream()
        .map(element -> new Subtopic(element.getName())).collect(
            Collectors.toList());

    topic.setSubtopics(subtopics);

    topics.save(topic);
    return topic;
  }
}
