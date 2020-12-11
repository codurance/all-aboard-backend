package com.codurance.allaboard.web.acceptance;

import com.codurance.allaboard.web.utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicDetailsFetchingFeature extends RestAssuredUtils {

    @LocalServerPort
    private int port;

    private static int EXISTING_TOPIC_ID = 1;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void given_get_can_access_endpoint() {
        RequestSpecification httpRequest = httpRequest();
        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s)",EXISTING_TOPIC_ID)));
        assertThat(response.statusCode(), is(200));
    }

}
