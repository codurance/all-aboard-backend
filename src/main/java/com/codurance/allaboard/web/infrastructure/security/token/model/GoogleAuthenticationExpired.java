package com.codurance.allaboard.web.infrastructure.security.token.model;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.util.Optional;

public class GoogleAuthenticationExpired implements GoogleAuthenticationResult {

  @Override
  public boolean isAuthenticated() {
    return false;
  }

  @Override
  public Optional<GoogleIdToken> getToken() {
    return Optional.empty();
  }
}
