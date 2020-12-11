package com.codurance.allaboard.web.controllers.topic;

import com.codurance.allaboard.core.actions.topic.FetchTopicById;
import com.codurance.allaboard.core.model.topic.Topic;
import java.util.Optional;

import com.codurance.allaboard.web.views.TopicDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    private final FetchTopicById fetchTopicById;

    @Autowired
    public TopicController(FetchTopicById fetchTopicById) {
        this.fetchTopicById = fetchTopicById;
    }

    @GetMapping("topic/{id}")
    public ResponseEntity<TopicDetailView> fetchTopicsById(@PathVariable long id) {
        Optional<TopicDetailView> optionalTopic = fetchTopicById.execute(id);
        return optionalTopic
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
