package com.codurance.allaboard.core.model.topic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
  private final Resources resources;

@Autowired
  public ResourceService(Resources resources) {
    this.resources = resources;
  }


  public List<Resource> saveResources(List<Resource> resourceList, Subtopic subtopic) {
    for (Resource resource : resourceList) {
      resource.setSubtopic(subtopic);
    }

    Iterable<Resource> resourceIterable = this.resources.saveAll(resourceList);
    return resourceIterableToResourceList(resourceIterable);

  }

  private List<Resource> resourceIterableToResourceList(Iterable<Resource> storedResources) {
    return StreamSupport
        .stream(storedResources.spliterator(), false)
        .collect(Collectors.toList());
  }
}
