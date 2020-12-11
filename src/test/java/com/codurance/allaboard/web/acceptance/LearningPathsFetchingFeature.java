package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.codurance.allaboard.web.utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
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
public class LearningPathsFetchingFeature extends RestAssuredUtils {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Sql(scripts = "classpath:stub-catalogue.sql")
  @Sql(scripts = "classpath:empty_catalogue_table.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
  void given_get_fetch_catalogue() throws IOException {
    RequestSpecification httpRequest = httpRequestWithoutAuthorizationHeader();
    Response response = httpRequest.get(apiV1Endpoint("learningpath"));

    JSONObject responseBody = buildResponseBody(response);
    JSONArray learningPaths = responseBody.getJSONArray("learningPaths");

    assertThat(response.statusCode(), is(200));
    assertThat(learningPaths.length(), is(2));
    assertThat(responseBody.toString(), is(expectedResponseBody()));
  }

  private String expectedResponseBody() throws IOException {
    StringBuilder sb = new StringBuilder();
    Path filePath = Paths.get("src", "test", "resources", "stub-catalogue.json");

    try (BufferedReader br = Files.newBufferedReader(
        filePath)) {
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    }
    return sb.toString();
  }
}
