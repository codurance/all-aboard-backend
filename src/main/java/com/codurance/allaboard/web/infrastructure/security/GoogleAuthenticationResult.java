package com.codurance.allaboard.web.infrastructure.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

public class GoogleAuthenticationResult {

  private final GoogleIdToken googleIdToken;
  private final boolean isAuthenticated;

  public GoogleAuthenticationResult(GoogleIdToken googleIdToken, boolean isAuthenticated) {
    this.googleIdToken = googleIdToken;
    this.isAuthenticated = isAuthenticated;
  }

  public GoogleIdToken getGoogleIdToken() {
    return googleIdToken;
  }

  public boolean isAuthenticated() {
    return isAuthenticated;
  }
}
