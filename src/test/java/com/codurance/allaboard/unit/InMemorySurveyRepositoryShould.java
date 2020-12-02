package com.codurance.allaboard.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

import com.codurance.allaboard.survey.infrastructure.repositories.InMemorySurveyRepository;
import com.codurance.allaboard.survey.infrastructure.repositories.SurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemorySurveyRepositoryShould {

  private SurveyRepository surveyRepository;
  private Survey survey;

  @BeforeEach
  void setUp() {
    surveyRepository = new InMemorySurveyRepository();
    survey = new Survey("user@codurance.com", "I prefer Udacity");
  }

  @Test
  void save_a_survey() {
    assertThat(surveyRepository.count(), is(0));

    surveyRepository.save(survey);

    assertThat(surveyRepository.count(), is(1));
  }

  @Test
  void get_a_survey_by_email() {
    surveyRepository.save(survey);
    final var allSurveysByEmail = surveyRepository.findAllSurveysByEmail("user@codurance.com");
    assertThat(allSurveysByEmail, hasItem(survey));
  }
}