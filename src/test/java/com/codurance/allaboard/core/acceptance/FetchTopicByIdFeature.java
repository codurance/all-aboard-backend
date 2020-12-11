package com.codurance.allaboard.core.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.web.controllers.topic.TopicController;
import java.util.Optional;

import com.codurance.allaboard.web.views.TopicDetailView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FetchTopicByIdFeature {

  @Mock
  private FetchTopicById fetchTopicById;

  private TopicController topicController;
  private final long NON_EXISTENT_TOPIC_ID = 9L;
  private final long EXISTING_TOPIC_ID = 1L;

  @BeforeEach
  void setUp() {
    topicController = new TopicController(fetchTopicById);
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_topic() {
    given(fetchTopicById.execute(NON_EXISTENT_TOPIC_ID))
        .willReturn(Optional.empty());

    ResponseEntity<TopicDetailView> responseEntity = topicController.fetchTopicsById(NON_EXISTENT_TOPIC_ID);

    verify(fetchTopicById, atLeastOnce()).execute(NON_EXISTENT_TOPIC_ID);
    assertThat(responseEntity.getStatusCode().value(), is(404));
  }

  @Test
  void answers_ok_when_found_if_asked_for_a_existing_topic() {

    given(fetchTopicById.execute(EXISTING_TOPIC_ID))
            .willReturn(Optional.of(new TopicDetailView()));

    ResponseEntity<TopicDetailView> responseEntity = topicController.fetchTopicsById(EXISTING_TOPIC_ID);

    verify(fetchTopicById, atLeastOnce()).execute(EXISTING_TOPIC_ID);
    assertThat(responseEntity.getStatusCode().value(), is(200));
  }
}
