package com.codurance.allaboard.survey.model;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class Survey implements Serializable {
  @NotEmpty(message = "cannot be null or empty")
  private final String email;
  @NotEmpty(message = "cannot be null or empty")
  private final String preference;

  public Survey(String email, String preference) {
    this.email = email;
    this.preference = preference;
  }

  public String getEmail() {
    return email;
  }

  public String getPreference() {
    return preference;
  }
}
