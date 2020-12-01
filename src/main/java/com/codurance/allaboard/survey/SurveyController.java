package com.codurance.allaboard.survey;

import com.codurance.allaboard.survey.model.Survey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

  @PostMapping("/survey")
  public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey) {
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }
}
