package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import java.util.HashSet;
import java.util.Set;

public class InMemorySurveyRepository implements SurveyRepository {

  private Set<Survey> surveys;

  public InMemorySurveyRepository() {
    this.surveys = new HashSet<>();
  }

  @Override
  public void save(Survey survey) {
    surveys.add(survey);
  }

  @Override
  public int count() {
    return surveys.size();
  }
}
