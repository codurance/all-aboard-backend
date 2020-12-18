package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.SubtopicDetailView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtopicService {

  private Subtopics subtopics;

  @Autowired
  public SubtopicService(Subtopics subtopics) {

    this.subtopics = subtopics;
  }

  public List<Subtopic> saveSubtopics(List<SubtopicDetailView> subtopicDetailViewList,
      Topic topic) {

    List<Subtopic> subtopicList = subtopicDetailViewList.stream()
        .map(element -> new Subtopic(topic, element.getName())).collect(
            Collectors.toList());

    Iterable<Subtopic> storedSubtopics = this.subtopics.saveAll(subtopicList);

    List<Subtopic> finalSubtopics = StreamSupport
        .stream(storedSubtopics.spliterator(), false)
        .collect(Collectors.toList());

    return finalSubtopics;
  }

}
