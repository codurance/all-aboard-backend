package com.codurance.allaboard.survey.model;

import java.io.Serializable;

public class Survey implements Serializable {
  private final String email;
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
