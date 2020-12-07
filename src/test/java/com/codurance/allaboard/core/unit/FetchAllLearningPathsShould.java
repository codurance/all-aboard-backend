package com.codurance.allaboard.core.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import java.util.List;
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
  void retrieve_all_learning_paths() {
    List<LearningPath> learningPathsFromRepository = List.of(new LearningPath());
    given(learningPathsRepo.findAll())
        .willReturn(learningPathsFromRepository);

    List<LearningPath> retrievedLearningPaths = fetchAllLearningPaths.execute();

    verify(learningPathsRepo, atLeastOnce()).findAll();
    assertThat(retrievedLearningPaths, is(learningPathsFromRepository));
  }
}
