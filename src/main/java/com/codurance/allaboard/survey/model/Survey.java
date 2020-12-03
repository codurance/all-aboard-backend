package com.codurance.allaboard.survey.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "surveys")
public class Survey implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotEmpty(message = "cannot be null or empty")
  @Column(nullable = false)
  private String email;

  @NotEmpty(message = "cannot be null or empty")
  @Column(nullable = false)
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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
