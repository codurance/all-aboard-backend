package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.TopicWithSubtopicsView;

public class TopicService {

  private Topics topics;

  public TopicService(Topics topics) {
    this.topics = topics;
  }

  public Topic storeTopicWithSubtopics(
      TopicWithSubtopicsView topicWithSubtopicsView) {

    Topic topic = new Topic(topicWithSubtopicsView.getName(),
        topicWithSubtopicsView.getDescription());

    topics.save(topic);
    return null;
  }
}
