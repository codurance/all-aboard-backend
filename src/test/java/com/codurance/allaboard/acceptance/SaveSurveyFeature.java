package com.codurance.allaboard.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SaveSurvey;
import com.codurance.allaboard.survey.services.SearchSurveys;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class SaveSurveyFeature {

  @Autowired
  private SearchSurveys searchSurveys;
  @Autowired
  private SaveSurvey saveSurvey;


  @Test
  void should_save_a_survey() {
    final String email = "user@codurance.com";
    final Survey survey = new Survey(email, "I prefer Udacity.");

    saveSurvey.saveSurvey(survey);

    final var dbSurvey = searchSurveys.getSurveysByEmail(email)
        .getSurveys()
        .get(0);

    assertThat(dbSurvey.getEmail(), is("user@codurance.com"));
  }
}
