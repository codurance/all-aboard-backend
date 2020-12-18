package com.codurance.allaboard.core.actions.topic;

import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.TopicService;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveTopic {


  private TopicService topicService;

  @Autowired
  public SaveTopic(TopicService topicService) {
    this.topicService = topicService;
  }


  public TopicWithSubtopicsView execute(TopicWithSubtopicsView topicWithSubtopicsView) {
    Topic topic = topicService.storeTopicWithSubtopics(topicWithSubtopicsView);

    List<SubtopicDetailView> subtopicsDetailView = topic.getSubtopics().stream()
        .map(item -> new SubtopicDetailView(item.getId(), item.getName())).collect(
            Collectors.toList());

    TopicWithSubtopicsView topicToReturn = new TopicWithSubtopicsView(topic.getName(),
        topic.getDescription(), subtopicsDetailView);
    topicToReturn.setId(topic.getId());

    return topicToReturn;
  }
}
