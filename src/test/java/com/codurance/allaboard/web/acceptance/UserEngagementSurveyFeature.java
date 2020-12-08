package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.codurance.allaboard.web.utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserEngagementSurveyFeature extends RestAssuredUtils {

  private final String EMAIL_FROM_TOKEN = "user@codurance.com";
  @LocalServerPort
  private int port;
  private JSONObject requestBody;
  private String email;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    email = "fabio.damico@codurance.com";
    requestBody = new JSONObject();
  }

  @Test
  void reject_an_invalid_user_engagement_survey() {
    requestBody.put("email", email);
    RequestSpecification request = httpRequestWithJSONContentType(requestBody);

    Response response = request.post("api/v1/survey");

    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(), is(400));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("preference"), is("cannot be null or empty"));
  }

  @Test
  void saves_a_survey() {
    requestBody.put("email", email);
    requestBody.put("preference", "I like to use Udacity");
    RequestSpecification request = httpRequestWithJSONContentType(requestBody);

    Response response = request.post("api/v1/survey");

    JSONObject responseBody = buildResponseBody(response);
    assertThat(response.statusCode(), is(201));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("preference"), is(requestBody.get("preference")));
  }

  @Test
  void save_a_survey_with_a_description_up_to_1500() {
    requestBody.put("email", email);
    requestBody.put("preference", StringUtils.repeat("f", 1500));
    RequestSpecification request = httpRequestWithJSONContentType(requestBody);

    Response response = request.post("api/v1/survey");

    JSONObject responseBody = buildResponseBody(response);
    assertThat(response.statusCode(), is(201));
    assertThat(response.getContentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("preference"), is(requestBody.get("preference")));
    assertThat(responseBody.get("email"), is(EMAIL_FROM_TOKEN));
  }

  @Test
  void reject_a_survey_with_a_description_bigger_than_1500() {
    requestBody.put("email", email);
    requestBody.put("preference", StringUtils.repeat("f", 1501));
    RequestSpecification request = httpRequestWithJSONContentType(requestBody);

    Response response = request.post("api/v1/survey");

    JSONObject responseBody = buildResponseBody(response);
    assertThat(response.getStatusCode(), is(400));
    assertThat(responseBody.get("preference"), is("Cannot be bigger than 1500 characters"));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
  }
}
