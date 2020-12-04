package com.codurance.allaboard.survey;

import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.model.Surveys;
import com.codurance.allaboard.survey.services.SurveyService;
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

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @PostMapping("/survey")
  public ResponseEntity<Survey> saveSurvey(HttpServletRequest request, @Valid @RequestBody Survey survey) {
    String email = (String) request.getAttribute("user_email");
    survey.setEmail(email);
    surveyService.saveSurvey(survey);
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }

  @GetMapping("/survey")
  public ResponseEntity<Surveys> getSurveysByEmail(@RequestParam String email) {
    Surveys surveys = surveyService.getSurveysByEmail(email);
    return new ResponseEntity<>(surveys, HttpStatus.OK);
  }
}
