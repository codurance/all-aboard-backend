package com.codurance.allaboard.survey.model;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class Survey implements Serializable {
  @NotEmpty(message = "cannot be null or empty")
  private String email;
  @NotEmpty(message = "cannot be null or empty")
  private String preference;

  public Survey(String email, String preference) {
    this.email = email;
    this.preference = preference;
  }

  public Survey() {
  }

  public String getEmail() {
    return email;
  }

  public String getPreference() {
    return preference;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPreference(String preference) {
    this.preference = preference;
  }
}
