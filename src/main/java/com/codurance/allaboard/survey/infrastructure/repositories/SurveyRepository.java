package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import java.util.Set;

public interface SurveyRepository {
  void save(Survey survey);

  int count();

  Set<Survey> findAllSurveysByEmail(String email);
}
