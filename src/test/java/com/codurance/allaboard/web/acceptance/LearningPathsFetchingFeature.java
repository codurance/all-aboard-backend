package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codurance.allaboard.acceptance.utils.WebAcceptanceTestTemplate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.jdbc.SqlMergeMode.MergeMode;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlMergeMode(MergeMode.MERGE)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class LearningPathsFetchingFeature extends WebAcceptanceTestTemplate {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Sql(scripts = "classpath:stub-catalogue.sql")
  void given_get_fetch_catalogue() throws IOException {
    RequestSpecification httpRequest = httpRequest();
    Response response = httpRequest.get(apiV1Endpoint("learningpath"));
    JSONObject expectedResponseBody = buildJsonObjectFromFile("stub-catalogue.json");

    JSONObject responseBody = buildResponseBody(response);
    JSONArray learningPaths = responseBody.getJSONArray("learningPaths");

    assertThat(response.statusCode(), is(200));
    assertThat(learningPaths.length(), is(2));
    assertTrue(responseBody.similar(expectedResponseBody));
  }

}
