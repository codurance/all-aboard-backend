package com.codurance.allaboard.config.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CorsFilter;
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

  @Bean
  public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
    return CorsFilterConfiguration
        .simpleCorsFilter(List.of("http://all-aboard-fe.s3-website.eu-west-2.amazonaws.com/",
            "http://localhost:8080"));
  }
}
