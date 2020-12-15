package com.codurance.allaboard.web.controllers.survey;

import com.codurance.allaboard.core.actions.survey.SaveSurvey;
import com.codurance.allaboard.core.model.survey.Survey;
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
    saveSurvey.execute(survey);
    return new ResponseEntity<>(survey, HttpStatus.CREATED);
  }
}
