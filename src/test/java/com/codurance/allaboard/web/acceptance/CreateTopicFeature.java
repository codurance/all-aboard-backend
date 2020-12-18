package com.codurance.allaboard.web.acceptance;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.codurance.allaboard.acceptance.utils.WebAcceptanceTestTemplate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
  private String name = "name";
  private String description = "description";
  private JSONArray subtopics;
  private JSONObject subtopic;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    requestBody = new JSONObject();
    subtopic = new JSONObject();
    subtopics = new JSONArray();
    subtopic.put("name", "subtopic name");
    subtopics.put(new JSONObject().put("name", "subtopic 1 name"));
    subtopics.put(new JSONObject().put("name", "subtopic  2 name"));

  }

  @Test
  void accept_a_valid_topic() {
    requestBody.put("name", name);
    requestBody.put("description", description);
    requestBody.put("subtopics", subtopics);

    RequestSpecification httpRequest = httpRequestWithJSONContentType(requestBody);
    Response response = httpRequest.post(apiV1Endpoint("topic"));
    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(), is(201));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("name"), is(name));
    assertThat(responseBody.get("description"), is(description));
  }


}
