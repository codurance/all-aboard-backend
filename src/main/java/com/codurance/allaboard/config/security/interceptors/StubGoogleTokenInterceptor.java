package com.codurance.allaboard.config.security.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
class StubGoogleTokenInterceptor implements TokenInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    request.setAttribute("user_email", "user@codurance.com");
    return true;
  }
}
