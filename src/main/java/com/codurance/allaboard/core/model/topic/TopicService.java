package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

  private Topics topics;
  private SubtopicService subtopicService;

  @Autowired
  public TopicService(Topics topics, SubtopicService subtopicService) {
    this.topics = topics;
    this.subtopicService = subtopicService;
  }

  public Topic storeTopic(TopicWithSubtopicsView topicWithSubtopicsView) {
    Topic topic = new Topic(topicWithSubtopicsView.getName(),
        topicWithSubtopicsView.getDescription());
    return topics.save(topic);
  }

  public Topic storeTopicWithSubtopics(
      TopicWithSubtopicsView topicWithSubtopicsView) {

    Topic topicStored = storeTopic(topicWithSubtopicsView);

    List<Subtopic> subtopics = subtopicService
        .saveSubtopics(topicWithSubtopicsView.getSubtopics(), topicStored);

    //topicStored.setSubtopics(subtopics);
    return topics.save(topicStored);
  }
}
