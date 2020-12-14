package com.codurance.allaboard.web.acceptance;

import com.codurance.allaboard.e2e.utils.WebAcceptanceE2ETestTemplate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicDetailsFetchingFeature extends WebAcceptanceE2ETestTemplate {

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
    @Sql(scripts = "classpath:empty_catalogue_table.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void can_access_parse_and_repond_without_resources() throws IOException {
        RequestSpecification httpRequest = httpRequest();

        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s", ID_OF_TOPIC_WITHOUT_RESOURCES)));
        JSONObject responseBody = buildResponseBody(response);

        assertThat(response.statusCode(), is(200));
        assertThat(responseBody.toString(), is(expectedResponseBody("stub-topic-with-subtopics-no-resources.json")));
    }

    @Test
    @Sql(scripts = "classpath:stub-topic-subtopics-resources.sql")
    @Sql(scripts = "classpath:empty_catalogue_table.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void can_access_parse_and_repond_with_resources() throws IOException {
        RequestSpecification httpRequest = httpRequest();

        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s", ID_OF_TOPIC_WITH_RESOURCES)));
        JSONObject responseBody = buildResponseBody(response);

        assertThat(response.statusCode(), is(200));
        assertThat(responseBody.toString(), is(expectedResponseBody("stub-topic-with-subtopics-with-resources.json")));
    }
}
