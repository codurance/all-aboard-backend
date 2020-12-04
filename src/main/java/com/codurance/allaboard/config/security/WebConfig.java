package com.codurance.allaboard.config.security;

import com.codurance.allaboard.config.security.interceptors.TokenInterceptor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final TokenInterceptor tokenInterceptor;

  @Autowired
  public WebConfig(TokenInterceptor tokenInterceptor) {
    this.tokenInterceptor = tokenInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tokenInterceptor);
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
    return CorsFilterConfiguration
        .simpleCorsFilter(List.of("http://all-aboard-fe.s3-website.eu-west-2.amazonaws.com",
            "http://localhost:8080"));
  }
}
