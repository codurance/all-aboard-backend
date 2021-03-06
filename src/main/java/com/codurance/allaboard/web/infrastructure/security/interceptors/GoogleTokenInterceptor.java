package com.codurance.allaboard.web.infrastructure.security.interceptors;

import com.codurance.allaboard.web.infrastructure.security.authenticators.GoogleTokenAuthenticator;
import com.codurance.allaboard.web.infrastructure.security.authenticators.model.GoogleAuthenticationResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Profile({"prod", "test-auth"})
@Service
public class GoogleTokenInterceptor implements TokenInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(GoogleTokenInterceptor.class);
  private final GoogleTokenAuthenticator googleTokenAuthenticator;

  @Autowired
  public GoogleTokenInterceptor(GoogleTokenAuthenticator googleTokenAuthenticator) {
    this.googleTokenAuthenticator = googleTokenAuthenticator;
  }


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String token = request.getHeader("Authorization");

    if (token == null) {
      logger.info("Authorization token is missing");
      return createUnauthorizedResponse(response);
    }

    if (token.isEmpty()) {
      logger.info("Authorization token is empty");
      return createUnauthorizedResponse(response);
    }

    GoogleAuthenticationResult googleAuthenticationResult = googleTokenAuthenticator
        .authenticateToken(token);

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

  private boolean createUnauthorizedResponse(HttpServletResponse response) {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }
}
