package com.codurance.allaboard.survey.services;

import com.codurance.allaboard.survey.infrastructure.repositories.SurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.model.Surveys;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveSurvey {

  private final SurveyRepository surveyRepository;

  @Autowired
  public SaveSurvey(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public void saveSurvey(Survey survey) {
    surveyRepository.save(survey);
  }


}
