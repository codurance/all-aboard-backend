package com.codurance.allaboard.learningpath.model;

import java.io.Serializable;

public class Catalogue implements Serializable {

    private String learningPaths;

    public Catalogue(String learningPaths) {
        this.learningPaths = learningPaths;
    }

    public String getLearningPaths() {
        return learningPaths;
    }
}
