package com.codurance.allaboard.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Profile("prod")
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final GoogleTokenAuthenticator googleTokenAuthenticator;

  @Autowired
  public WebConfig(GoogleTokenAuthenticator googleTokenAuthenticator) {
    this.googleTokenAuthenticator = googleTokenAuthenticator;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(googleTokenAuthenticator);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods("GET", "POST", "OPTIONS")
        .allowedOrigins("http://localhost:8080", "http://all-aboard-fe.s3-website.eu-west-2.amazonaws.com/");

  }
}
