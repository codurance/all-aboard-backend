package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codurance.allaboard.core.model.topic.Resource;
import com.codurance.allaboard.core.model.topic.ResourceService;
import com.codurance.allaboard.core.model.topic.Resources;
import com.codurance.allaboard.core.model.topic.Subtopic;
import com.codurance.allaboard.web.views.ResourceView;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceShould {

  @Mock
  Resources resources;
  @InjectMocks
  ResourceService resourceService;

  @Test
  void save_resources_and_return_them() {
    ResourceView resource = mock(ResourceView.class);
    List<ResourceView> resourceList = List.of(resource);
    Subtopic subtopic = mock(Subtopic.class);
    Iterable<Resource> resourceIterator = Collections
        .singletonList(new Resource(subtopic, resource.getLabel(), resource.getUrl()));

    when(resources.saveAll(any())).thenReturn(resourceIterator);

    List<Resource> expectedResourceList = resourceService.saveResources(resourceList, subtopic);

    assertThat(expectedResourceList.size(), is(resourceList.size()));
    verify(resources, atLeastOnce()).saveAll(any());
  }
}
