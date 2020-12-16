package com.codurance.allaboard.core.actions.topic;

import com.codurance.allaboard.core.model.topic.Topic;
import com.codurance.allaboard.core.model.topic.Topics;
import com.codurance.allaboard.web.views.TopicView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaveTopic {

  private final Topics topics;

  @Autowired
  public SaveTopic(Topics topics) {
    this.topics = topics;
  }


  public void execute(TopicView topicView) {
    throw new UnsupportedOperationException();
  }
}
