package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Topic;

public class TopicOverviewView {

    private Long id;
    private String name;
    private String description;

    private TopicOverviewView(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static TopicOverviewView from(Topic topic) {
        return new TopicOverviewView(topic.getId(), topic.getName(), topic.getDescription());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
