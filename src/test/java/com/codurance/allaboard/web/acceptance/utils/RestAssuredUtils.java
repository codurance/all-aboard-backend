package com.codurance.allaboard.web.acceptance.utils;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class RestAssuredUtils {

  private final String authorization = "Authorization";

  public JSONObject buildResponseBody(Response response) {
    return new JSONObject(response.getBody().print());
  }

  public RequestSpecification httpRequestWithJSONContentType(JSONObject jsonObject) {
    return given()
        .contentType(ContentType.JSON)
        .body(jsonObject.toString());
  }

  public RequestSpecification httpRequestWithoutAuthorizationHeader() {
    return given();
  }

  public RequestSpecification httpRequestWithEmptyAuthorizationHeader() {
    return given()
        .header(authorization, "");
  }

  public RequestSpecification httpRequestWithInvalidAuthorizationHeader() {
    return given()
        .header(authorization, "invalid token");
  }
}