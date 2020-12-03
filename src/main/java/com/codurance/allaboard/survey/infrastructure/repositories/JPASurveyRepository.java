package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPASurveyRepository extends SurveyRepository, CrudRepository<Survey, Long> {
  Set<Survey> findSurveysByEmail(String email);
}
