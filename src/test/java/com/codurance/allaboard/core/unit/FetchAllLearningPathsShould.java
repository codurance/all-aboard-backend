package com.codurance.allaboard.core.unit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FetchAllLearningPathsShould {

  @Mock
  private LearningPaths learningPathsRepo;
  @InjectMocks
  private FetchAllLearningPaths fetchAllLearningPaths;

  @Test
  void retrieve_all_learningPaths_from_a_repository() {
    fetchAllLearningPaths.execute();

    verify(learningPathsRepo, atLeastOnce()).findAll();
  }
}
