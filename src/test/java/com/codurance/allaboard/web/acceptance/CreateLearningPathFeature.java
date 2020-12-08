package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.codurance.allaboard.web.acceptance.utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateLearningPathFeature extends RestAssuredUtils {

  @LocalServerPort private int port;
  private JSONObject requestBody;
  private String name;
  private String description;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    requestBody = new JSONObject();
    name = "Stub name";
    description = "Stub description";
  }

  @Test
  void save_a_learningpath_given_the_valid_request() {
    requestBody.put("name", name);
    requestBody.put("description", description);
    RequestSpecification httpRequest = httpRequestWithJSONContentType(requestBody);

    Response response = httpRequest.post("api/v1/learningpath");
    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(),is(201));
    assertThat(response.contentType(), is(ContentType.JSON.toString()));
    assertThat(responseBody.get("name"), is(name));
    assertThat(responseBody.get("description"),is(description));
  }
}
