package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicService {

  private final Topics topics;
  private final SubtopicService subtopicService;

  @Autowired
  public TopicService(Topics topics, SubtopicService subtopicService) {
    this.topics = topics;
    this.subtopicService = subtopicService;
  }

  public Topic storeTopicWithSubtopics(
      TopicWithSubtopicsView topicWithSubtopicsView) {

    Topic topicToSave = topicWithSubtopicViewToTopic(topicWithSubtopicsView);
    Topic topicStored = topics.save(topicToSave);

    List<Subtopic> subtopics = subtopicService
        .saveSubtopics(topicWithSubtopicsView.getSubtopics(), topicStored);
    topicStored.setSubtopics(subtopics);

    return topics.save(topicStored);
  }

  private Topic topicWithSubtopicViewToTopic(TopicWithSubtopicsView topicWithSubtopicsView) {
    return new Topic(topicWithSubtopicsView.getName(),
        topicWithSubtopicsView.getDescription());
  }
}
