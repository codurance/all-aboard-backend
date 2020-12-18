package com.codurance.allaboard.core.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

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
  void call_subtopics_repository_to_store_subtopics() {
    List<SubtopicDetailView> subtopicDetailViewList = List.of(new SubtopicDetailView());
    Topic topic = new Topic();

    subtopicService.saveSubtopics(subtopicDetailViewList, topic);
    verify(subtopics, atLeastOnce()).saveAll(any());
  }
}
