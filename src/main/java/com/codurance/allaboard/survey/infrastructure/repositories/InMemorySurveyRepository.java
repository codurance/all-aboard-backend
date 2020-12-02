package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;

public class InMemorySurveyRepository implements SurveyRepository {

  @Override
  public void save(Survey survey) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String count() {
    throw new UnsupportedOperationException();
  }
}
