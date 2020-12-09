package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import java.util.Optional;
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
  void return_null_when_learningpath_with_give_id_does_not_exist() {
    given(learningPaths.findById(id)).willReturn(Optional.empty());
    LearningPath learningPath = fetchLearningPathById.findById(id);
    assertThat(learningPath, is(nullValue()));
    verify(learningPaths, atLeastOnce()).findById(id);
  }
}
