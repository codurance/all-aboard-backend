package com.codurance.allaboard.core.actions.topic;

import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.TopicService;
import com.codurance.allaboard.web.views.ResourceView;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveTopic {

  private final TopicService topicService;

  @Autowired
  public SaveTopic(TopicService topicService) {
    this.topicService = topicService;
  }

  public TopicWithSubtopicsView execute(TopicWithSubtopicsView topicWithSubtopicsView) {
    Topic topic = topicService.storeTopicWithSubtopics(topicWithSubtopicsView);

    List<SubtopicDetailView> subtopicsDetailView = buildSubtopicDetailViewList(topic);

    return buildTopicWithSubtopicView(topic, subtopicsDetailView);
  }

  private TopicWithSubtopicsView buildTopicWithSubtopicView(Topic topic,
      List<SubtopicDetailView> subtopicsDetailView) {
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView(topic.getName(),
        topic.getDescription(), subtopicsDetailView);
    topicWithSubtopicsView.setId(topic.getId());

    return topicWithSubtopicsView;
  }

  private List<SubtopicDetailView> buildSubtopicDetailViewList(Topic topic) {
    return topic.getSubtopics().stream()
        .map(item -> new SubtopicDetailView(item.getId(), item.getName(),
            buildResourceViewList(item))).collect(
            Collectors.toList());
  }

  private List<ResourceView> buildResourceViewList(Subtopic subtopic) {
    return subtopic.getResources().stream()
        .map(item -> new ResourceView(item.getId(), item.getLabel(), item.getUrl())).collect(
            Collectors.toList());
  }
}
