package com.codurance.allaboard.web.views;

import static java.util.stream.Collectors.toList;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LearningPathDetailView implements Serializable {

  private long id;
  @NotEmpty(message = "Cannot be null or empty")
  private String name;
  @NotEmpty(message = "Cannot be null or empty")
  @Size(max = 1500, message = "Cannot be bigger than 1500 characters")
  private String description;
  private List<TopicView> topics;

  private LearningPathDetailView() {
  }

  private LearningPathDetailView(long id, String name, String description, List<TopicView> topics) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.topics = topics;
  }

  //[X] in here pass TopicOverviewView insetad of Topic - also List not Set -> we care about ordering.
  //[X] check parts in which we're returning topics inside of view -> should be using dedicated view.
  // once done @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  // get back to FetchTopicByIdShould TDD. <- this is potentially misleading (BECAUSE TOPIC MEANS NOW TWO THINGS: overview & details)
  // to be still implemented Subtopics (currently has only name propery)
  // to be still implemented Resources (currently nothing)
  public static LearningPathDetailView from(LearningPath learningPath) {
    return new LearningPathDetailView(
      learningPath.getId(),
      learningPath.getName(),
      learningPath.getDescription(),
      learningPath.getTopics().stream().map(TopicView::from).collect(toList())
    );
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public long getId() {
    return id;
  }

  public List<TopicView> getTopics() {
    return topics;
  }
}
