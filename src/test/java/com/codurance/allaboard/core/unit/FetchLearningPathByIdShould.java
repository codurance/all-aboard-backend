package com.codurance.allaboard.core.unit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
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

  @Test
  void query_the_repository_for_a_learningPath() {
    final long id = 1L;

    fetchLearningPathById.execute(id);

    verify(learningPaths, atLeastOnce()).findById(id);
  }
}
