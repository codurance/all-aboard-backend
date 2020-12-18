package com.codurance.allaboard.web.acceptance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codurance.allaboard.acceptance.utils.WebAcceptanceTestTemplate;
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
public class TopicDetailsFetchingFeature extends WebAcceptanceTestTemplate {

    @LocalServerPort
    private int port;

    private static int ID_OF_TOPIC_WITHOUT_RESOURCES = 1;
    private static int ID_OF_TOPIC_WITH_RESOURCES = 2;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Sql(scripts = "classpath:stub-topic-subtopics.sql")
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void can_access_parse_and_respond_without_resources() throws IOException {
        RequestSpecification httpRequest = httpRequest();

        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s", ID_OF_TOPIC_WITHOUT_RESOURCES)));
        JSONObject responseBody = buildResponseBody(response);
        JSONObject expectedResponseBody = buildJsonObjectFromFile("stub-topic-with-subtopics-no-resources.json");

        assertThat(response.statusCode(), is(200));
        assertTrue(responseBody.similar(expectedResponseBody));
    }

    @Test
    @Sql(scripts = "classpath:stub-topic-subtopics-resources.sql")
    @Sql(scripts = "classpath:cleanup.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void can_access_parse_and_respond_with_resources() throws IOException {
        RequestSpecification httpRequest = httpRequest();

        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s", ID_OF_TOPIC_WITH_RESOURCES)));
        JSONObject responseBody = buildResponseBody(response);
        JSONObject expectedResponseBody = buildJsonObjectFromFile("stub-topic-with-subtopics-with-resources.json");

        assertThat(response.statusCode(), is(200));
        assertTrue(responseBody.similar(expectedResponseBody));
    }
}
