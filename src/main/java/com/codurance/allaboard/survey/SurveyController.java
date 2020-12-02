package com.codurance.allaboard.survey;

import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SurveyService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @PostMapping("/survey")
  public ResponseEntity<Survey> saveSurvey(@Valid @RequestBody Survey survey) {
    surveyService.saveSurvey(survey);
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }
}
