package com.codurance.allaboard.web.infrastructure.interceptors.token;

import com.codurance.allaboard.web.infrastructure.security.token.model.GoogleAuthenticationExpired;
import com.codurance.allaboard.web.infrastructure.security.token.model.GoogleAuthenticationInvalid;
import com.codurance.allaboard.web.infrastructure.security.token.model.GoogleAuthenticationResult;
import com.codurance.allaboard.web.infrastructure.security.token.model.GoogleAuthenticationValid;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Profile({"prod", "test-auth"})
@Service
public class GoogleTokenInterceptor implements TokenInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(GoogleTokenInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String token = request.getHeader("Authorization");

    if (tokenIsEmptyOrNull(token)) {
      logger.info("Caused by empty or null Authorization token provided");
      return createUnauthorizedResponse(response);
    }

    GoogleAuthenticationResult googleAuthenticationResult = authenticateToken(token);

    if (googleAuthenticationResult.isAuthenticated()) {
      return createAuthorizedResponse(request, googleAuthenticationResult);
    }
    return createUnauthorizedResponse(response);
  }

  private boolean createAuthorizedResponse(HttpServletRequest request,
      GoogleAuthenticationResult googleAuthenticationResult) {
    googleAuthenticationResult
        .getToken()
        .ifPresent(token -> {
          request.setAttribute("user_email", token.getPayload().getEmail());
        });
    return true;
  }

  private boolean tokenIsEmptyOrNull(String token) {
    return (token == null) || token.isEmpty();
  }

  private boolean createUnauthorizedResponse(HttpServletResponse response) {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  private GoogleAuthenticationResult authenticateToken(String token) {
    GoogleIdTokenVerifier verifier = buildGoogleIdTokenVerifier();
    GoogleIdToken googleIdToken;
    try {
      googleIdToken = verifier.verify(token);
      if (googleIdToken == null) {
        logger.info("Caused by expired Authorization token");
        return new GoogleAuthenticationExpired();
      }
    } catch (GeneralSecurityException | IOException | IllegalArgumentException exception) {
      logger.info("Caused by invalid Authorization token");
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
