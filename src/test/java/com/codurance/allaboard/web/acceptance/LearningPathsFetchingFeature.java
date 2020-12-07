package com.codurance.allaboard.web.acceptance;

import io.restassured.RestAssured;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

import static io.restassured.RestAssured.given;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LearningPathsFetchingFeature {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Sql(scripts = "classpath:stub-catalogue.sql")
    void given_get_fetch_catalogue() throws IOException {
        ;
        RequestSpecification httpRequest = httpRequest();
        Response response = httpRequest.get("api/v1/learningpath");

        JSONObject responseBody = buildResponseBody(response);
        JSONArray learningPaths = responseBody.getJSONArray("learningPaths");

        assertThat(response.statusCode(), is(200));
        assertThat(learningPaths.length(), is(2));
        assertThat(responseBody.toString(), is(expectedResponseBody()));
    }

    private String expectedResponseBody() throws IOException {
      StringBuilder sb = new StringBuilder();
      Path filePath = Paths.get("src", "main", "resources", "stub-catalogue.json");

      try (BufferedReader br = Files.newBufferedReader(
          filePath)) {
        String line;
        while ((line = br.readLine()) != null) {
          sb.append(line);
        }
      }

      return sb.toString();
    }

    private RequestSpecification httpRequest() {
        return given();
    }

    private JSONObject buildResponseBody(Response response) {
        return new JSONObject(response.getBody().print());
    }
}
