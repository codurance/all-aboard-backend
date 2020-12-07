package com.codurance.allaboard.web.views;

import com.codurance.allaboard.core.model.catalogue.LearningPath;
import java.io.Serializable;
import java.util.List;

public class Catalogue implements Serializable {

    private final List<LearningPath> learningPaths;

    public Catalogue(List<LearningPath> learningPaths) {
        this.learningPaths = learningPaths;
    }

    public List<LearningPath> getLearningPaths() {
        return learningPaths;
    }
}
