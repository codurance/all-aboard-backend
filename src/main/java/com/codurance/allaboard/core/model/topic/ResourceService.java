package com.codurance.allaboard.core.model.topic;

import com.codurance.allaboard.web.views.ResourceView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceService {

  private final Resources resources;

  @Autowired
  public ResourceService(Resources resources) {
    this.resources = resources;
  }


  public List<Resource> saveResources(List<ResourceView> resourceViewList, Subtopic subtopic) {

    List<Resource> resourceList = resourceViewList.stream()
        .map(item -> new Resource(subtopic, item.getLabel(), item.getUrl())).collect(
            Collectors.toList());

    Iterable<Resource> resourceIterable = this.resources.saveAll(resourceList);

   return resourceIterableToResourceList(resourceIterable);
  }

  private List<Resource> resourceIterableToResourceList(Iterable<Resource> storedResources) {
    return StreamSupport
        .stream(storedResources.spliterator(), false)
        .collect(Collectors.toList());
  }
}
