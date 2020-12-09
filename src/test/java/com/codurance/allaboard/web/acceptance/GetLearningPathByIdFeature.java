package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.codurance.allaboard.web.utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class GetLearningPathByIdFeature extends RestAssuredUtils {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_learning_path() {
    RequestSpecification request = httpRequest();

    Response response = request.get("api/v1/learningpath/1");
    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(), is(404));
    //assertThat(responseBody.get("message"), is("The requested Learning Path does not exist."));
  }
}
