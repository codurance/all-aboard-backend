package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.topic.Resource;
import java.io.Serializable;

public class ResourceView implements Serializable {

  private long id;
  private String label;
  private String url;

  public ResourceView(long id, String label, String url) {
    this.id = id;
    this.label = label;
    this.url = url;
  }

  public ResourceView(String label, String url) {
    this.label = label;
    this.url = url;
  }

  public ResourceView() {
  }

  public static ResourceView from(Resource resource) {
    return new ResourceView(resource.getId(), resource.getLabel(), resource.getUrl());
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
