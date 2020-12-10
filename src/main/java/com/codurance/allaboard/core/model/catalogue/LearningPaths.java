package com.codurance.allaboard.core.model.catalogue;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPaths extends CrudRepository<LearningPath, Long> {

}
