package com.codurance.allaboard.web.acceptance;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.codurance.allaboard.acceptance.utils.WebAcceptanceTestTemplate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateTopicFeature extends WebAcceptanceTestTemplate {

  @LocalServerPort
  private int port;
  private JSONObject requestBody;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    requestBody = new JSONObject();
  }

  @Test
  void accept_a_valid_topic() throws IOException {

    JSONObject requestBody = buildJsonObjectFromFile("stub-create-topic.json");
    RequestSpecification httpRequest = httpRequestWithJSONContentType(requestBody);
    Response response = httpRequest.post(apiV1Endpoint("topic"));
    JSONObject responseBody = buildResponseBody(response);


    assertThat(response.statusCode(), is(201));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("name"), is("topic name"));
    assertThat(responseBody.get("description"), is("topic description"));
  }

  @Test
  void error_on_empty_fields() {
    RequestSpecification httpRequest = httpRequestWithJSONContentType(requestBody);

    Response response = httpRequest.post(apiV1Endpoint("topic"));
    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(), is(400));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("description"), is("Cannot be null or empty"));
    assertThat(responseBody.get("name"), is("Cannot be null or empty"));
    assertThat(responseBody.get("subtopics"), is("Cannot be null"));
  }
}
