package com.codurance.allaboard.acceptance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
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
  }

  @Test
  void reject_an_invalid_user_engagement_survey() {
    jsonBody.put("email", email);

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("api/v1/survey")
        .then()
        .statusCode(400)
        .body("preference", is(nullOrEmptyMessage))
        .contentType(ContentType.JSON);
  }

  @Test
  void saves_and_retrieves_surveys_by_email() {
    jsonBody.put("email", email);
    jsonBody.put("preference", "I like to use Udacity");

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("api/v1/survey")
        .then()
        .statusCode(201)
        .body("preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);

    given()
        .queryParam("email", email)
        .get("api/v1/survey")
        .then()
        .statusCode(200)
        .body("surveys[0].email", is(jsonBody.get("email")))
        .body("surveys[0].preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);
  }

  @Test
  void save_a_survey_with_a_description_up_to_1500() {
    jsonBody.put("email", email);
    jsonBody.put("preference", StringUtils.repeat("f", 1500));

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("api/v1/survey")
        .then()
        .statusCode(201)
        .body("preference", is(jsonBody.get("preference")))
        .contentType(ContentType.JSON);
  }

  @Test
  void reject_a_survey_with_a_description_bigger_than_1500() {
    jsonBody.put("email", email);
    jsonBody.put("preference", StringUtils.repeat("f", 1501));

    given()
        .body(jsonBody.toString())
        .contentType(ContentType.JSON)
        .post("api/v1/survey")
        .then()
        .statusCode(400)
        .body("preference", is("Cannot be bigger than 1500 characters"))
        .contentType(ContentType.JSON);
  }
}
