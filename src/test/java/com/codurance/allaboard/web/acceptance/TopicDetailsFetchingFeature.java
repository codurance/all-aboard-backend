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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class TopicDetailsFetchingFeature extends WebAcceptanceE2ETestTemplate {

    @LocalServerPort
    private int port;

    private static int EXISTING_TOPIC_ID = 1;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void given_get_can_access_endpoint() throws IOException {
        RequestSpecification httpRequest = httpRequest();

        Response response = httpRequest.get(apiV1Endpoint(String.format("topic/%s)", EXISTING_TOPIC_ID)));
        JSONObject responseBody = buildResponseBody(response);

        assertThat(response.statusCode(), is(200));
        assertThat(responseBody.toString(), is(expectedResponseBody()));
    }

    private String expectedResponseBody() throws IOException {
        StringBuilder sb = new StringBuilder();
        Path filePath = Paths.get("src", "test", "resources", "stub-topic.json");

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
