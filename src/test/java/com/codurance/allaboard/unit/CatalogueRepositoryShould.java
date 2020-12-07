package com.codurance.allaboard.unit;

import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ActiveProfiles("dev")
@DataJpaTest
@Sql(scripts = "classpath:stub-catalogue.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CatalogueRepositoryShould {

    @Autowired
    private LearningPaths learningPaths;

    @Test
    void find_all_available_learning_paths_names_and_descriptions() {
        Iterable<LearningPath> learningPathsResult = learningPaths.findAll();
        List<LearningPath> learningPaths = new ArrayList<>();
        learningPathsResult.iterator().forEachRemaining(learningPaths::add);
        assertThat(learningPaths.size(), is(2));
    }
}
