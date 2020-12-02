package com.codurance.allaboard.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.codurance.allaboard.survey.infrastructure.repositories.InMemorySurveyRepository;
import com.codurance.allaboard.survey.infrastructure.repositories.SurveyRepository;
import com.codurance.allaboard.survey.model.Survey;
import org.junit.jupiter.api.Test;

class InMemorySurveyRepositoryShould {

  @Test
  void save_a_survey() {
    SurveyRepository surveyRepository = new InMemorySurveyRepository();
    assertThat(surveyRepository.count(), is(0));

    surveyRepository.save(new Survey("user@codurance.com", "I prefer Udacity"));
    
    assertThat(surveyRepository.count(), is(1));
  }
}