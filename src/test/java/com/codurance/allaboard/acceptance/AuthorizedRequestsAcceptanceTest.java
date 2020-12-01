package com.codurance.allaboard.acceptance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizedRequestsAcceptanceTest {

  @LocalServerPort
  private int port;

  @Test
  @Disabled
  void authenticated_requests_do_not_get_unauthorized_status_code() {

    given()
        .header(
            "Authorization",
            "YOUR_TOKEN_HERE"
        )
        .port(port)
        .get("/demo")
        .then()
        .statusCode(not(HttpStatus.UNAUTHORIZED.value()));
  }

  @Test
  @Disabled
  void unauthenticated_requests_get_unauthorized_status_code() {
    given()
        .port(port)
        .get("/demo")
        .then()
        .statusCode(HttpStatus.UNAUTHORIZED.value());
  }
}
