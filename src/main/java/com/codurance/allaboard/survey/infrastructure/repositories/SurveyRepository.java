package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import java.util.Set;


public interface SurveyRepository {

  Object save(Survey survey);

  long count();

  Set<Survey> findSurveysByEmail(String email);
}
