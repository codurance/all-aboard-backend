package com.codurance.allaboard.acceptance;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.survey.infrastructure.repositories.Surveys;
import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SaveSurvey;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveSurveyFeature {

  @Mock
  private Surveys surveys;

  @InjectMocks
  private SaveSurvey saveSurvey;

  @Test
  void should_save_a_survey() {
    final String email = "user@codurance.com";
    final Survey survey = new Survey(email, "I prefer Udacity.");

    saveSurvey.saveSurvey(survey);

    verify(surveys, atLeastOnce()).save(survey);
  }
}
