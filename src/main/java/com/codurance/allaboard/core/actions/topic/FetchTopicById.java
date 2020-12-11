package com.codurance.allaboard.core.actions.topic;

import com.codurance.allaboard.core.model.topic.Topics;
import java.util.Optional;

import com.codurance.allaboard.web.views.TopicDetailView;
import org.springframework.stereotype.Service;

@Service
public class FetchTopicById {

  private final Topics topics;

  public FetchTopicById(Topics topics) {
    this.topics = topics;
  }

  public Optional<TopicDetailView> execute(Long id) {
    throw new UnsupportedOperationException();
  }
}
