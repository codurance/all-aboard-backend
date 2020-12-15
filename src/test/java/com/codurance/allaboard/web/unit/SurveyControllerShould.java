package com.codurance.allaboard.web.unit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.survey.SaveSurvey;
import com.codurance.allaboard.core.model.survey.Survey;
import com.codurance.allaboard.core.model.survey.Surveys;
import com.codurance.allaboard.web.controllers.survey.SurveyController;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SurveyControllerShould {

  @Mock
  private HttpServletRequest request;

  @Mock
  private Surveys surveys;

  @InjectMocks
  private SaveSurvey saveSurvey;

  private SurveyController surveyController;
  private Survey survey;

  @BeforeEach
  void setUp() {
    saveSurvey = new SaveSurvey(surveys);
    surveyController = new SurveyController(saveSurvey);
    survey = new Survey();
  }

  @Test
  void save_a_survey() {
    surveyController.saveSurvey(request, survey);
    verify(surveys, atLeastOnce()).save(survey);
  }
}
