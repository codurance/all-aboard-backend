package com.codurance.allaboard.core.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.model.topic.TopicService;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TopicServirceShould {

  private TopicService topicService;
  @Mock
  private Topics topics;
  private String name;
  private String description;
  private List<SubtopicDetailView> subtopics;

  @BeforeEach
  void setUp() {
    topicService = new TopicService(topics);
    name = "name";
    description = "description";
    subtopics = List.of();
  }

  @Test
  void call_the_repository_to_store_the_topic() {
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView();

    topicService.storeTopicWithSubtopics(topicWithSubtopicsView);

    verify(topics, atLeastOnce()).save(any());
  }
}
