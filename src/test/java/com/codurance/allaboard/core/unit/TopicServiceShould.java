package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.core.model.topic.SubtopicService;
import com.codurance.allaboard.core.model.topic.Subtopics;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.TopicService;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TopicServiceShould {

  private TopicService topicService;
  @Mock
  private Topics topics;
  @Mock
  private SubtopicService subtopicService;
  private String name;
  private String description;
  private TopicWithSubtopicsView topicWithSubtopicsView;


  @BeforeEach
  void setUp() {
    topicService = new TopicService(topics, subtopicService);
    name = "name";
    description = "description";
    List<SubtopicDetailView> subtopicDetailViews = List
        .of(new SubtopicDetailView("first subtopic"), new SubtopicDetailView("second subtopic"));
    topicWithSubtopicsView = new TopicWithSubtopicsView();
    topicWithSubtopicsView.setName(name);
    topicWithSubtopicsView.setDescription(description);
    topicWithSubtopicsView.setSubtopics(subtopicDetailViews);
  }

  @Test
  void call_subtopic_service_to_store_subtopics() {
    when(topics.save(any())).thenReturn(mock(Topic.class));
    topicService.storeTopicWithSubtopics(topicWithSubtopicsView);
    verify(subtopicService, atLeastOnce()).saveSubtopics(any(),any());
  }


}
