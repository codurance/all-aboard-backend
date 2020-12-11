package com.codurance.allaboard.core.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.controllers.topic.TopicController;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@Disabled
public class FetchTopicByIdFeature {

  @Mock
  private Topics topics;
  private TopicController topicController;
  private final long NON_EXISTENT_TOPIC_ID = 9L;

  @BeforeEach
  void setUp() {
    topicController = new TopicController(new FetchTopicById(topics));
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_topic() {
    given(topics.findById(NON_EXISTENT_TOPIC_ID))
        .willReturn(Optional.empty());

    ResponseEntity<Optional<Topic>> responseEntity = topicController.fetchTopicsById(NON_EXISTENT_TOPIC_ID);

    verify(topics, atLeastOnce()).findById(NON_EXISTENT_TOPIC_ID);
    assertThat(responseEntity.getStatusCode().value(), is(404));
  }
}
