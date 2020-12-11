package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.codurance.allaboard.web.utils.WebAcceptanceTestTemplate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test-auth")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoogleTokenValidationFeature extends WebAcceptanceTestTemplate {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void deny_requests_without_authorization_header() {
    RequestSpecification request = httpRequestWithoutAuthorizationHeader();

    Response response = request.post(apiV1Endpoint("survey"));

    assertThat(response.statusCode(), is(401));
  }

  @Test
  void deny_requests_with_empty_authorization_header() {
    RequestSpecification request = httpRequestWithEmptyAuthorizationHeader();

    Response response = request.post(apiV1Endpoint("survey"));

    assertThat(response.statusCode(), is(401));
  }

  @Test
  void deny_requests_with_invalid_authorization_header() {
    RequestSpecification request = httpRequestWithInvalidAuthorizationHeader();

    Response response = request.post(apiV1Endpoint("survey"));

    assertThat(response.statusCode(), is(401));
  }
}
