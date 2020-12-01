package com.codurance.allaboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final GoogleTokenAuthenticator googleTokenAuthenticator;

  public WebConfig(GoogleTokenAuthenticator googleTokenAuthenticator) {
    this.googleTokenAuthenticator = googleTokenAuthenticator;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(googleTokenAuthenticator);
  }
}
