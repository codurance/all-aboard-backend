package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Resource;

public class ResourceView {

    private final long id;
    private final String label;
    private final String url;

    private ResourceView(long id, String label, String url) {
        this.id = id;
        this.label = label;
        this.url = url;
    }

    public static ResourceView from(Resource resource) {
        return new ResourceView(resource.getId(), resource.getName(), resource.getUrl());
    }

    public long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
