package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.SubtopicDetailView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtopicService {

  private final Subtopics subtopics;
  private final ResourceService resourceService;

  @Autowired
  public SubtopicService(Subtopics subtopics,
      ResourceService resourceService) {
    this.subtopics = subtopics;
    this.resourceService = resourceService;
  }

  public List<Subtopic> saveSubtopics(List<SubtopicDetailView> subtopicDetailViewList,
      Topic topic) {
    List<Subtopic> subtopicList = subtopicDetailViewListToSubtopicList(subtopicDetailViewList,
        topic);
    Iterable<Subtopic> storedIterableSubtopics = this.subtopics.saveAll(subtopicList);

    subtopicList = subtopicIterableToSubtopicList(storedIterableSubtopics);
    subtopicList = storeResources(subtopicList);

    return subtopicList;
  }

  private List<Subtopic> storeResources(List<Subtopic> subtopicList) {
    for (Subtopic subtopic : subtopicList) {
      List<Resource> resourceList = resourceService
          .saveResources(subtopic.getResources(), subtopic);
      subtopic.setResources(resourceList);
    }

    Iterable<Subtopic> subtopicIterable = this.subtopics.saveAll(subtopicList);
    return subtopicIterableToSubtopicList(subtopicIterable);
  }


  private List<Subtopic> subtopicIterableToSubtopicList(Iterable<Subtopic> storedSubtopics) {
    return StreamSupport
        .stream(storedSubtopics.spliterator(), false)
        .collect(Collectors.toList());
  }

  private List<Subtopic> subtopicDetailViewListToSubtopicList(
      List<SubtopicDetailView> subtopicDetailViewList, Topic topic) {
    return subtopicDetailViewList.stream()
        .map(element -> new Subtopic(topic, element.getName())).collect(
            Collectors.toList());
  }

}
