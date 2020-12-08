package com.codurance.allaboard.web.controllers.learningpath;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.web.views.Catalogue;
import com.codurance.allaboard.web.views.LearningPathView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningPathController {

  private final FetchAllLearningPaths fetchAllLearningPaths;

  @Autowired
  public LearningPathController(FetchAllLearningPaths fetchAllLearningPaths) {
    this.fetchAllLearningPaths = fetchAllLearningPaths;
  }

  @GetMapping("/learningpath")
  public ResponseEntity<Catalogue> provideCatalog() {
    Catalogue catalogue = new Catalogue(fetchAllLearningPaths.execute());
    return ResponseEntity.ok(catalogue);
  }

  @PostMapping("/learningpath")
  public ResponseEntity<LearningPathView> createLearningPath(
      @Valid @RequestBody LearningPathView learningPathView) {
    return new ResponseEntity<>(learningPathView, HttpStatus.CREATED);
  }
}
