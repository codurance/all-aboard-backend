package com.codurance.allaboard.acceptance;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("prod")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoogleTokenValidationFeature {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void deny_requests_without_authorization_header() {
    given()
        .post("/survey")
        .then()
        .statusCode(401);
  }

  @Test
  void deny_requests_with_empty_authorization_header() {
    given()
        .header("Authorization", "")
        .post("/survey")
        .then()
        .statusCode(401);
  }
}
