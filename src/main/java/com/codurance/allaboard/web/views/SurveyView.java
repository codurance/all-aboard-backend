package com.codurance.allaboard.web.views;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SurveyView implements Serializable {

  @NotEmpty(message = "cannot be null or empty")
  @Size(max = 1500, message = "Cannot be bigger than 1500 characters")
  private String preference;

  public SurveyView(String preference) {
    this.preference = preference;
  }

  public SurveyView() {
  }

  public String getPreference() {
    return preference;
  }
}
