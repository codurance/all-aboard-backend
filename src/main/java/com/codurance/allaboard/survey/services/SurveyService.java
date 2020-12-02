package com.codurance.allaboard.survey.services;

import com.codurance.allaboard.survey.infrastructure.repositories.SurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

  private final SurveyRepository surveyRepository;

  @Autowired
  public SurveyService(SurveyRepository surveyRepository) {
    this.surveyRepository = surveyRepository;
  }

  public void saveSurvey(Survey survey) {
    surveyRepository.save(survey);
  }

  public Set<Survey> getSurveysByEmail(String email) {
    return surveyRepository.findAllSurveysByEmail(email);
  }
}
