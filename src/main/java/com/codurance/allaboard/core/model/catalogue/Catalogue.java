package com.codurance.allaboard.core.model.catalogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements Serializable {

    private List<LearningPath> learningPaths;

    public Catalogue(Iterable<LearningPath> learningPathsQuerySet) {
        this.learningPaths = new ArrayList<>();
        learningPathsQuerySet.iterator().forEachRemaining(learningPaths::add);
    }

    public List<LearningPath> getLearningPaths() {
        return learningPaths;
    }
}
