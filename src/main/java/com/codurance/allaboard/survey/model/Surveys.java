package com.codurance.allaboard.survey.model;

import java.util.List;

public class Surveys {

  private List<Survey> surveys;

  public Surveys() {
  }

  public Surveys(List<Survey> surveys) {
    this.surveys = surveys;
  }

  public List<Survey> getSurveys() {
    return surveys;
  }

  public void setSurveys(List<Survey> surveys) {
    this.surveys = surveys;
  }
}
