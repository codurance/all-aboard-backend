package com.codurance.allaboard.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GoogleTokenAuthenticator implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String token = request.getHeader("Authorization");
    try {
      authenticateToken(token, request);
    } catch (GeneralSecurityException | IOException | NullPointerException exception) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      return false;
    }
    return true;
  }

  private void authenticateToken(String token, HttpServletRequest request)
      throws GeneralSecurityException, IOException {
    GoogleIdTokenVerifier verifier = buildGoogleIdTokenVerifier();
    GoogleIdToken idToken = verifier.verify(token);
    request.getSession().setAttribute("name", idToken.getPayload().get("name"));
  }

  private GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
    return new GoogleIdTokenVerifier
        .Builder(new NetHttpTransport(), new JacksonFactory())
        .build();
  }
}
