package com.codurance.allaboard.web.infrastructure.interceptors.token;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Profile({"prod", "test-auth"})
@Service
public class GoogleTokenInterceptor implements TokenInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String token = request.getHeader("Authorization");
    if(tokenIsEmptyOrNull(token)){
      return createUnauthorizedResponse(response);
    }
    try {
      GoogleIdToken googleIdToken = authenticateToken(token);
      request.setAttribute("user_email", googleIdToken.getPayload().getEmail());
    } catch (GeneralSecurityException | IOException | NullPointerException | IllegalArgumentException exception) {
      return createUnauthorizedResponse(response);
    }
    return true;
  }

  private boolean tokenIsEmptyOrNull(String token) {
    return (token == null) || token.isEmpty();
  }

  private boolean createUnauthorizedResponse(HttpServletResponse response) {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

  private GoogleIdToken authenticateToken(String token)
      throws GeneralSecurityException, IOException {
    GoogleIdTokenVerifier verifier = buildGoogleIdTokenVerifier();
    return verifier.verify(token);
  }

  protected GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
    return new GoogleIdTokenVerifier
        .Builder(new NetHttpTransport(), new JacksonFactory())
        .build();
  }
}
