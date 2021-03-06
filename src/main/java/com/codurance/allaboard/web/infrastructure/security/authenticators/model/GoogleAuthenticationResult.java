package com.codurance.allaboard.web.infrastructure.security.authenticators.model;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.util.Optional;

public interface GoogleAuthenticationResult {

  boolean isAuthenticated();

  Optional<GoogleIdToken> getToken();
}
