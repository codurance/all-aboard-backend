package com.codurance.allaboard.web.controllers.learningPath;

import com.codurance.allaboard.web.views.Catalogue;
import com.codurance.allaboard.core.model.catalogue.LearningPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningPathController {

    LearningPaths learningPaths;

    @Autowired
    public LearningPathController(LearningPaths learningPaths) {
        this.learningPaths = learningPaths;
    }

    @GetMapping("/learningpath")
    public ResponseEntity<Catalogue> provideCatalog() {
        Catalogue catalogue = new Catalogue(learningPaths.findAll());
        return ResponseEntity.ok(catalogue);
    }
}
