package com.codurance.allaboard.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.survey.infrastructure.repositories.Surveys;
import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SaveSurvey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SaveSurveyShould {

  @Mock
  private Surveys surveys;

  @InjectMocks
  private SaveSurvey saveSurvey;

  private Survey survey;

  final String email = "user@codurance.com";

  @BeforeEach
  void setUp() {
    survey = new Survey(email, "any");
  }

  @Test
  void save_a_survey() {
    saveSurvey.saveSurvey(survey);
    verify(surveys, atLeastOnce()).save(survey);
  }
}
