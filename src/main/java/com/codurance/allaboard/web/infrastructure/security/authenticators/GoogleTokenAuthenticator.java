package com.codurance.allaboard.web.infrastructure.security.authenticators;

import com.codurance.allaboard.web.infrastructure.security.authenticators.model.GoogleAuthenticationExpired;
import com.codurance.allaboard.web.infrastructure.security.authenticators.model.GoogleAuthenticationInvalid;
import com.codurance.allaboard.web.infrastructure.security.authenticators.model.GoogleAuthenticationResult;
import com.codurance.allaboard.web.infrastructure.security.authenticators.model.GoogleAuthenticationValid;
import com.codurance.allaboard.web.infrastructure.security.interceptors.GoogleTokenInterceptor;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GoogleTokenAuthenticator {

  private static final Logger logger = LoggerFactory.getLogger(GoogleTokenInterceptor.class);

  public GoogleAuthenticationResult authenticateToken(String token) {
    GoogleIdTokenVerifier verifier = buildGoogleIdTokenVerifier();
    GoogleIdToken googleIdToken;
    try {
      googleIdToken = verifier.verify(token);
      if (googleIdToken == null) {
        logger.info("Authorization token provided is expired");
        return new GoogleAuthenticationExpired();
      }
    } catch (GeneralSecurityException | IOException | IllegalArgumentException exception) {
      logger.info("Authorization token provided is invalid");
      return new GoogleAuthenticationInvalid();
    }
    return new GoogleAuthenticationValid(googleIdToken);
  }

  protected GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
    return new GoogleIdTokenVerifier
        .Builder(new NetHttpTransport(), new JacksonFactory())
        .build();
  }
}