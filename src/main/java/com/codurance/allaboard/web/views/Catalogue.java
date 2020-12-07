package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements Serializable {

    private final List<LearningPath> learningPaths;

    public Catalogue(Iterable<LearningPath> learningPathsQuerySet) {
        this.learningPaths = new ArrayList<>();
        learningPathsQuerySet.iterator().forEachRemaining(learningPaths::add);
    }

    public List<LearningPath> getLearningPaths() {
        return learningPaths;
    }
}
