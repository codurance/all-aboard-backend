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

  private final String nullOrEmptyMessage = "cannot be null or empty";
  @LocalServerPort
  private int port;
  private JSONObject jsonBody;
  private String email;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    email = "fabio.damico@codurance.com";
    jsonBody = new JSONObject();
    jsonBody.put("email", email);
    jsonBody.put("preference", "I like to use Udacity");
  }

  @Test
  void accept_a_user_engagement_survey() {

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("/survey")
        .then()
        .statusCode(201)
        .body("preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);
  }

  @Test
  void reject_an_invalid_user_engagement_survey() {
    jsonBody.put("email", email);

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("/survey")
        .then()
        .statusCode(400)
        .body("preference", is(nullOrEmptyMessage))
        .contentType(ContentType.JSON);
  }

  @Test
  void retrieve_surveys_by_email() {
    given()
        .queryParam("email", email)
        .get("/survey")
        .then()
        .statusCode(200)
        .body("surveys[0].email", is(jsonBody.get("email")))
        .body("surveys[0].preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);
  }
}
