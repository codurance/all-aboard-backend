package com.codurance.allaboard.survey.services;

import com.codurance.allaboard.survey.infrastructure.repositories.Surveys;
import com.codurance.allaboard.survey.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveSurvey {

  private final Surveys surveys;

  @Autowired
  public SaveSurvey(Surveys surveys) {
    this.surveys = surveys;
  }

  public void save(Survey survey) {
    surveys.save(survey);
  }
}
