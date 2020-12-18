package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Topic;

public class TopicView {

    private Long id;
    private String name;
    private String description;

    private TopicView(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TopicView() {
    }

    public static TopicView from(Topic topic) {
        return new TopicView(topic.getId(), topic.getName(), topic.getDescription());
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
