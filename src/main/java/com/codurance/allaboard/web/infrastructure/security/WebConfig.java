package com.codurance.allaboard.web.infrastructure.security;

import com.codurance.allaboard.web.infrastructure.security.interceptors.TokenInterceptor;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${cors.domain.allowed}")
  private String[] allowedDomains;

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
        .simpleCorsFilter(Arrays.asList(allowedDomains));
  }
}
