package com.codurance.allaboard.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import com.codurance.allaboard.survey.infrastructure.repositories.InMemorySurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SurveyServiceFeature {

  private SurveyService surveyService;

  @BeforeEach
  void setUp() {
    surveyService = new SurveyService(new InMemorySurveyRepository());
  }

  @Test
  void should_save_a_survey() {
    final String email = "user@codurance.com";
    final Survey survey = new Survey(email, "I prefer Udacity.");

    surveyService.saveSurvey(survey);

    assertThat(surveyService.getSurveysByEmail(email).getSurveys(), contains(survey));
  }
}
