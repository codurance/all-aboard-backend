package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;

public interface SurveyRepository {
  void save(Survey survey);

  String count();
}
