package com.codurance.allaboard.core.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codurance.allaboard.core.actions.topic.SaveTopic;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.TopicService;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveTopicShould {


  @Mock
  private TopicService topicService;
  @InjectMocks
  private SaveTopic saveTopic;

  @Test
  void call_the_service_and_receive_a_topic() {
    List<SubtopicDetailView> subtopicDetailViewList = List.of(
        new SubtopicDetailView("I'm a subtopic"));
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView("topic",
        "description", subtopicDetailViewList);
    when(topicService.storeTopicWithSubtopics(any())).thenReturn(mock(Topic.class));

    saveTopic.execute(topicWithSubtopicsView);
    verify(topicService, atLeastOnce()).storeTopicWithSubtopics(any());
  }
}
