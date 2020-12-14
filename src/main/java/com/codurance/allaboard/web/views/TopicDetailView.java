package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.core.model.topic.Topic;

import java.util.List;

public class TopicDetailView {
    private String name;
    private List<Subtopic> subtopics;

    private TopicDetailView(Topic topic) {
        name = topic.getName();
        subtopics = topic.getSubtopics();
    }

    public static TopicDetailView from(Topic topic) {
        return new TopicDetailView(topic);
    }

    public String getName() {
        return name;
    }

    public List<Subtopic> getSubtopics() {
        return subtopics;
    }
}
