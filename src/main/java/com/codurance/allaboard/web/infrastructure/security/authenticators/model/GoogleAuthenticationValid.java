package com.codurance.allaboard.web.infrastructure.security.authenticators.model;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.util.Optional;

public class GoogleAuthenticationValid implements GoogleAuthenticationResult {

  private final GoogleIdToken googleIdToken;

  public GoogleAuthenticationValid(GoogleIdToken googleIdToken) {
    this.googleIdToken = googleIdToken;
  }

  public GoogleIdToken getGoogleIdToken() {
    return googleIdToken;
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }

  @Override
  public Optional<GoogleIdToken> getToken() {
    return Optional.of(googleIdToken);
  }
}
