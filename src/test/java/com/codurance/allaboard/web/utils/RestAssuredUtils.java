package com.codurance.allaboard.web.utils;

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
}