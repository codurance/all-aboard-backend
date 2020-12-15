package com.codurance.allaboard.e2e.utils;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class WebAcceptanceTestTemplate {

  @Value("${server.servlet.context-path}")
  private String apiUrlPrefix;

  private final String authorization = "Authorization";

  public String apiV1Endpoint(String endpoint) {
    if (endpoint != null && endpoint.length() > 0) {
      if (endpoint.endsWith("/")) {
          endpoint = endpoint.substring(0, endpoint.length() - 1);
      }

      if (endpoint.startsWith("/")) {
        endpoint = endpoint.substring(1);
      }
    }
    return String.format("%s/%s", apiUrlPrefix, endpoint);
  }

  public JSONObject buildResponseBody(Response response) {
    return new JSONObject(response.getBody().print());
  }

  public RequestSpecification httpRequest() {
    return given();
  }

  public RequestSpecification httpRequestWithJSONContentType(JSONObject jsonObject) {
    return httpRequest()
        .contentType(ContentType.JSON)
        .body(jsonObject.toString());
  }

  public RequestSpecification httpRequestWithoutAuthorizationHeader() {
    return httpRequest();
  }

  public RequestSpecification httpRequestWithEmptyAuthorizationHeader() {
    return httpRequest()
        .header(authorization, "");
  }

  public RequestSpecification httpRequestWithInvalidAuthorizationHeader() {
    return httpRequest()
        .header(authorization, "invalid token");
  }

  protected String expectedResponseBody(String jsonFileName) throws IOException {
    StringBuilder sb = new StringBuilder();
    Path filePath = Paths.get("src", "test", "resources", jsonFileName);

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