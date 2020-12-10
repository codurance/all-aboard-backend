package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.core.model.topic.Topic;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FetchLearningPathByIdShould {

  @Mock
  private LearningPaths learningPaths;
  @InjectMocks
  private FetchLearningPathById fetchLearningPathById;
  private long id = 1L;

  @Test
  void return_no_learningpath_if_requested_id_does_not_exist() {
    Optional<LearningPath> optional = Optional.empty();
    given(learningPaths.findById(id))
        .willReturn(optional);

    Optional<LearningPath> retrievedOptional = fetchLearningPathById.findById(id);

    assertThat(retrievedOptional, is(optional));
    verify(learningPaths, atLeastOnce()).findById(id);
  }

  @Test
  void return_existing_learningpath_when_requested_by_id() {
    LearningPath expectedLearningPath =
        new LearningPath(id, "some title", "some description", Set
            .of(new Topic(1L, "title", "description")));
    Optional<LearningPath> optional = Optional.of(expectedLearningPath);
    given(learningPaths.findById(id))
        .willReturn(optional);

    Optional<LearningPath> retrievedOptional = fetchLearningPathById.findById(id);

    assertThat(retrievedOptional, is(optional));
    verify(learningPaths, atLeastOnce()).findById(id);
  }
}
