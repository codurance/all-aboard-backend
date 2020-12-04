package com.codurance.allaboard.learningpath;

import com.codurance.allaboard.learningpath.model.Catalogue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningPathController {

    @GetMapping("/learningpath")
    public ResponseEntity<Catalogue> provideCatalog() {
        Catalogue catalogue = new Catalogue("catalogue");
        return ResponseEntity.ok(catalogue);
    }
}
