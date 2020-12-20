package com.codurance.allaboard.web.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.actions.topic.SaveTopic;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.controllers.topic.TopicController;
import com.codurance.allaboard.web.views.ResourceView;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicDetailView;
import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TopicControllerShould {

  @Mock
  private FetchTopicById fetchTopicById;
  @Mock
  Topics topics;
  @Mock
  private SaveTopic saveTopic;

  private TopicController topicController;
  private final long NON_EXISTENT_TOPIC_ID = 9L;
  private final long EXISTING_TOPIC_ID = 1L;

  @BeforeEach
  void setUp() {
    topicController = new TopicController(fetchTopicById, saveTopic);
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_topic() {
    ResponseEntity<TopicDetailView> responseEntity = topicController
        .fetchTopicsById(NON_EXISTENT_TOPIC_ID);

    verify(fetchTopicById, atLeastOnce()).execute(NON_EXISTENT_TOPIC_ID);
    assertThat(responseEntity.getStatusCode().value(), is(404));
  }

  @Test
  void answers_ok_when_found_if_asked_for_a_existing_topic() {
    given(fetchTopicById.execute(EXISTING_TOPIC_ID))
        .willReturn(Optional.of(mock(Topic.class)));

    ResponseEntity<TopicDetailView> responseEntity = topicController
        .fetchTopicsById(EXISTING_TOPIC_ID);

    verify(fetchTopicById, atLeastOnce()).execute(EXISTING_TOPIC_ID);
    assertThat(responseEntity.getStatusCode().value(), is(200));
  }

  @Test
  void save_a_topic() {
    topicController.createTopic(new TopicWithSubtopicsView());
    verify(saveTopic, atLeastOnce()).execute(any());
  }

  @Test
  void save_topic_with_subtopics() {
    List<ResourceView> resourceViewList = List.of(new ResourceView("label", "url"));
    List<SubtopicDetailView> subtopicDetailViewList = List
        .of(new SubtopicDetailView("subtopic name", resourceViewList));
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView("topic name",
        "topic description", subtopicDetailViewList);

    topicController
        .createTopic(topicWithSubtopicsView);

    verify(saveTopic, atLeastOnce()).execute(any());
  }
}

