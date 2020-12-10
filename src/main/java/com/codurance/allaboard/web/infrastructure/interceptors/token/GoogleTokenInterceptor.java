package com.codurance.allaboard.web.infrastructure.interceptors.token;

import com.codurance.allaboard.web.infrastructure.security.GoogleAuthenticationResult;
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

    if(tokenIsEmptyOrNull(token)){
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
    GoogleIdToken googleIdToken = googleAuthenticationResult.getGoogleIdToken();
    request.setAttribute("user_email", googleIdToken.getPayload().getEmail());
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
    GoogleIdToken googleIdToken = null;
    boolean isAuthenticated = false;

    try {
      googleIdToken = verifier.verify(token);
      isAuthenticated = true;
    } catch (GeneralSecurityException | IOException | IllegalArgumentException exception) {
      logger.error("Error");
    }

    return new GoogleAuthenticationResult(googleIdToken, isAuthenticated);
  }

  protected GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
    return new GoogleIdTokenVerifier
        .Builder(new NetHttpTransport(), new JacksonFactory())
        .build();
  }

}
