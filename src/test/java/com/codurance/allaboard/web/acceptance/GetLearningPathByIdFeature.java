package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.codurance.allaboard.e2e.utils.WebAcceptanceE2ETestTemplate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetLearningPathByIdFeature extends WebAcceptanceE2ETestTemplate {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void answers_not_found_if_asked_for_a_nonexistent_learning_path() {
    RequestSpecification request = httpRequest();

    Response response = request.get(apiV1Endpoint("learningpath/1"));

    assertThat(response.statusCode(), is(404));
  }

  @Test
  @Sql(scripts = "classpath:stub-catalogue.sql")
  void answers_with_learning_path_if_asked_for_an_existent_one() throws IOException {
    RequestSpecification request = httpRequest();

    Response response = request.get(apiV1Endpoint("/learningpath/1"));
    JSONObject responseBody = buildResponseBody(response);

    assertThat(response.statusCode(), is(200));
    assertThat(responseBody.toString(), is(expectedResponseBody("stub-learningpath.json")));
  }
}
