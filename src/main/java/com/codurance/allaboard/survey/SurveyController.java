package com.codurance.allaboard.survey;

import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.model.Surveys;
import com.codurance.allaboard.survey.services.SaveSurvey;
import com.codurance.allaboard.survey.services.SearchSurveys;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

  private final SaveSurvey saveSurvey;
  private final SearchSurveys searchSurveys;

  @Autowired
  public SurveyController(SaveSurvey saveSurvey,
      SearchSurveys searchSurveys) {
    this.saveSurvey = saveSurvey;
    this.searchSurveys = searchSurveys;
  }

  @PostMapping("/survey")
  public ResponseEntity<Survey> saveSurvey(HttpServletRequest request, @Valid @RequestBody Survey survey) {
    String email = (String) request.getAttribute("user_email");
    survey.setEmail(email);
    saveSurvey.saveSurvey(survey);
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }

  @GetMapping("/survey")
  public ResponseEntity<Surveys> getSurveysByEmail(@RequestParam String email) {
    Surveys surveys = searchSurveys.getSurveysByEmail(email);
    return new ResponseEntity<>(surveys, HttpStatus.OK);
  }
}
