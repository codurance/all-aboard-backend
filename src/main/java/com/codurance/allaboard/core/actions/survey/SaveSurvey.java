package com.codurance.allaboard.core.actions.survey;

import com.codurance.allaboard.core.model.survey.Surveys;
import com.codurance.allaboard.core.model.survey.Survey;
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
