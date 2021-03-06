package com.codurance.allaboard.web.infrastructure.security.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev | test")
@Service
class StubGoogleTokenInterceptor implements TokenInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    request.setAttribute("user_email", "user@codurance.com");
    return true;
  }
}
