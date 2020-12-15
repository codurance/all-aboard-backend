package com.codurance.allaboard.core.unit;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.codurance.allaboard.core.actions.survey.SaveSurvey;
import com.codurance.allaboard.core.model.survey.Survey;
import com.codurance.allaboard.core.model.survey.Surveys;
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


  @Test
  void save_a_survey() {
    Survey survey = mock(Survey.class);

    saveSurvey.save(survey);

    verify(surveys, atLeastOnce()).save(survey);
  }
}
