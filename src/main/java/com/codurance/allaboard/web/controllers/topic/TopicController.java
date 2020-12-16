package com.codurance.allaboard.web.controllers.topic;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.actions.topic.SaveTopic;
import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.web.views.SubtopicDetailView;
import com.codurance.allaboard.web.views.TopicDetailView;

import java.util.List;
import java.util.Optional;

import com.codurance.allaboard.web.views.TopicWithSubtopicsView;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TopicController {

  private final FetchTopicById fetchTopicById;
  private SaveTopic saveTopic;


  @Autowired
  public TopicController(FetchTopicById fetchTopicById,
      SaveTopic saveTopic) {
    this.fetchTopicById = fetchTopicById;
    this.saveTopic = saveTopic;
  }

  @GetMapping("topic/{id}")
  public ResponseEntity<TopicDetailView> fetchTopicsById(@PathVariable long id) {
    Optional<Topic> topic = fetchTopicById.execute(id);
    if (topic.isPresent()) {
      return ResponseEntity.ok(TopicDetailView.from(topic.get()));
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("topic")
  public ResponseEntity<TopicWithSubtopicsView> createTopic(
      @Valid @RequestBody TopicWithSubtopicsView topicWithSubtopicsView) {

    saveTopic.execute(topicWithSubtopicsView);
    return new ResponseEntity<>(topicWithSubtopicsView, HttpStatus.CREATED);
  }

}
