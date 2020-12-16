package com.codurance.allaboard.web.controllers.learningpath;

import com.codurance.allaboard.core.actions.learningpath.FetchAllLearningPaths;
import com.codurance.allaboard.core.actions.learningpath.FetchLearningPathById;
import com.codurance.allaboard.core.actions.learningpath.SaveLearningPath;
import com.codurance.allaboard.core.model.catalogue.LearningPath;
import com.codurance.allaboard.web.views.CatalogueView;
import com.codurance.allaboard.web.views.LearningPathDetailView;
import com.codurance.allaboard.web.views.LearningPathView;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(LearningPathController.class);

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
  public ResponseEntity<CatalogueView> provideCatalog() {
    CatalogueView catalogue = CatalogueView.from(fetchAllLearningPaths.execute());
    return ResponseEntity.ok(catalogue);
  }

  @PostMapping("/learningpath")
  public ResponseEntity<LearningPathView> createLearningPath(
      @Valid @RequestBody LearningPathView learningPathView) {
    saveLearningPath.execute(
        new LearningPath(learningPathView.getName(), learningPathView.getDescription()));
    return new ResponseEntity<>(learningPathView, HttpStatus.CREATED);
  }

  @PostMapping("/fulllearningpath")
  public void createFullLearningPath() {
      throw new UnsupportedOperationException();
  }

  @GetMapping("/learningpath/{id}")
  public ResponseEntity<LearningPathDetailView> getById(@PathVariable Long id) {
    Optional<LearningPath> learningPath = fetchLearningPathById.execute(id);

    if (learningPath.isEmpty()) {
      logger.info("Learning path with id: [{}] not found", id.toString());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    LearningPathDetailView learningPathDetailView = LearningPathDetailView.from(learningPath.get());
    return new ResponseEntity<>(learningPathDetailView, HttpStatus.OK);
  }
}
