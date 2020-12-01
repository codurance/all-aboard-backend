package com.codurance.allaboard.acceptance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEngagementSurveyFeature {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void save_a_user_engagement_survey() {
    JSONObject jsonBody = new JSONObject();
    jsonBody.append("email", "fabio.damico@codurance.com");
    jsonBody.append("preference", "I like to use Udacity");

    given()
        .body(jsonBody)
        .contentType(ContentType.JSON)
        .post("/survey")
        .then()
        .statusCode(201)
        .body("preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);
  }
}
