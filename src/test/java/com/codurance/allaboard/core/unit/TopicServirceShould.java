package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.core.model.topic.Topic;
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
    subtopics = List
        .of(new SubtopicDetailView("first subtopic"), new SubtopicDetailView("second subtopic"));
  }

  @Test
  void call_the_repository_to_store_the_topic() {
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView();
    topicWithSubtopicsView.setName(name);
    topicWithSubtopicsView.setDescription(description);
    topicWithSubtopicsView.setSubtopics(subtopics);

    topicService.storeTopicWithSubtopics(topicWithSubtopicsView);

    verify(topics, atLeastOnce()).save(any());
  }

  @Test
  void should_return_a_topic_with_subtopics() {
    TopicWithSubtopicsView topicWithSubtopicsView = new TopicWithSubtopicsView();
    topicWithSubtopicsView.setName(name);
    topicWithSubtopicsView.setDescription(description);
    topicWithSubtopicsView.setSubtopics(subtopics);

    Topic topic = topicService.storeTopicWithSubtopics(topicWithSubtopicsView);
    Subtopic firstSubtopic = topic.getSubtopics().get(0);
    Subtopic secondSubtopic = topic.getSubtopics().get(1);

    assertThat(topic.getName(), is(name));
    assertThat(topic.getDescription(), is(description));
    assertThat(firstSubtopic.getName(), is("first subtopic"));
    assertThat(secondSubtopic.getName(), is("second subtopic"));
  }
}
