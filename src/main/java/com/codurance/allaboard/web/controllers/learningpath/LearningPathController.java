package com.codurance.allaboard.web.controllers.learningpath;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.web.views.Catalogue;
import com.codurance.allaboard.web.views.LearningPathView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningPathController {

  private final FetchAllLearningPaths fetchAllLearningPaths;
  private final SaveLearningPath saveLearningPath;
  private final FetchLearningPathById fetchLearningPathById;

  @Autowired
  public LearningPathController(
      FetchAllLearningPaths fetchAllLearningPaths,
      SaveLearningPath saveLearningPath,
      FetchLearningPathById fetchLearningPathById) {
    this.fetchAllLearningPaths = fetchAllLearningPaths;
    this.saveLearningPath = saveLearningPath;
    this.fetchLearningPathById = fetchLearningPathById;
  }

  @GetMapping("/learningpath")
  public ResponseEntity<Catalogue> provideCatalog() {
    Catalogue catalogue = new Catalogue(fetchAllLearningPaths.getAll());
    return ResponseEntity.ok(catalogue);
  }

  @PostMapping("/learningpath")
  public ResponseEntity<LearningPathView> createLearningPath(
      @Valid @RequestBody LearningPathView learningPathView) {
    saveLearningPath.save(
        new LearningPath(learningPathView.getName(), learningPathView.getDescription()));
    return new ResponseEntity<>(learningPathView, HttpStatus.CREATED);
  }

  @GetMapping("/learningpath/{id}")
  public ResponseEntity<LearningPathView> getById(@PathVariable Long id) {
    LearningPath learningPath = fetchLearningPathById.findById(id);
    if (learningPath == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    LearningPathView learningPathView = new LearningPathView(learningPath.getName(),
        learningPath.getDescription());
    return new ResponseEntity<>(learningPathView, HttpStatus.OK);
  }
}
