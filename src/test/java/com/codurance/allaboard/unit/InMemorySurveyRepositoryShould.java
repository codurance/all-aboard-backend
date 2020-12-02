package com.codurance.allaboard.unit;

import com.codurance.allaboard.survey.infrastructure.repositories.InMemorySurveyRepository;
import com.codurance.allaboard.survey.infrastructure.repositories.SurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InMemorySurveyRepositoryShould {

  @Test
  @Disabled
  void save_a_survey() {
    SurveyRepository surveyRepository = new InMemorySurveyRepository();

    surveyRepository.save(new Survey("user@codurance.com", "I prefer Udacity"));

    // assert?
  }
}