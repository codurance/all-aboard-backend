package com.codurance.allaboard.survey.infrastructure.repositories;

import com.codurance.allaboard.survey.model.Survey;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemorySurveyRepository implements SurveyRepository {

  private final Set<Survey> surveys;

  public InMemorySurveyRepository() {
    this.surveys = new HashSet<>();
  }

  @Override
  public Boolean save(Survey survey) {
    return surveys.add(survey);
  }

  @Override
  public long count() {
    return surveys.size();
  }

  @Override
  public Set<Survey> findSurveysByEmail(String email) {
    return surveys
        .stream()
        .filter(survey -> survey.getEmail().equals(email))
        .collect(Collectors.toSet());
  }
}
