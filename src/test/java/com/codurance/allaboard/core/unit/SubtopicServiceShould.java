package com.codurance.allaboard.core.unit;

import static io.restassured.RestAssured.given;
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
import com.codurance.allaboard.web.views.SubtopicDetailView;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubtopicServiceShould {

  @Mock
  Subtopics subtopics;
  private SubtopicService subtopicService;

  @BeforeEach
  void setUp() {
    subtopicService = new SubtopicService(subtopics);
  }

  @Test
  void save_and_return_the_subtopics() {
    List<SubtopicDetailView> subtopicDetailViewList = List.of(new SubtopicDetailView());
    Topic topic = new Topic();
    Subtopic subtopic = mock(Subtopic.class);
    List<Subtopic> subtopicList = List.of(subtopic);
    when(subtopics.saveAll(any())).thenReturn(subtopicList);

    List<Subtopic> subtopicSavedList = subtopicService.saveSubtopics(subtopicDetailViewList, topic);

    assertThat(subtopicSavedList, is(subtopicList));
    verify(subtopics, atLeastOnce()).saveAll(any());
  }
}
