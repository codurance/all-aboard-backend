package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Subtopic;
import java.util.List;
import java.util.stream.Collectors;

public class SubtopicDetailView {

    private long id;
    private String name;
    private List<ResourceView> resources;

    public SubtopicDetailView() {
    }

    private SubtopicDetailView(long id, String name, List<ResourceView> resources) {
        this.id = id;
        this.name = name;
        this.resources = resources;
    }

    public SubtopicDetailView(String name) {
        this.name = name;
    }

    public static SubtopicDetailView from(Subtopic subtopic) {
        return new SubtopicDetailView(
            subtopic.getId(),
            subtopic.getName(),
            subtopic.getResources().stream().map(ResourceView::from).collect(Collectors.toList())
        );
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ResourceView> getResources() {
        return resources;
    }
}
