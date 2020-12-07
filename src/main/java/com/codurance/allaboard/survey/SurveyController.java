package com.codurance.allaboard.survey;

import com.codurance.allaboard.survey.model.Survey;
import com.codurance.allaboard.survey.services.SaveSurvey;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

  private final SaveSurvey saveSurvey;

  @Autowired
  public SurveyController(SaveSurvey saveSurvey) {
    this.saveSurvey = saveSurvey;
  }

  @PostMapping("/survey")
  public ResponseEntity<Survey> saveSurvey(HttpServletRequest request,
      @Valid @RequestBody Survey survey) {
    String email = (String) request.getAttribute("user_email");
    survey.setEmail(email);
    saveSurvey.saveSurvey(survey);
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }
}
