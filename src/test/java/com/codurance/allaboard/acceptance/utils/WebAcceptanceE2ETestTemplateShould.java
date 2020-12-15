package com.codurance.allaboard.acceptance.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class WebAcceptanceE2ETestTemplateShould {

    WebAcceptanceTestTemplate utils;

    @BeforeEach
    private void setUp() {
        this.utils = new WebAcceptanceTestTemplate();
        ReflectionTestUtils.setField(utils, "apiUrlPrefix", "apiVersion");
    }

    @Test
    void createCorrectlyTheEndpoint() {
        assertThat(utils.apiV1Endpoint("foo/"), is("apiVersion/foo"));
        assertThat(utils.apiV1Endpoint("/foo/"), is("apiVersion/foo"));
        assertThat(utils.apiV1Endpoint("/foo"), is("apiVersion/foo"));
        assertThat(utils.apiV1Endpoint("foo"), is("apiVersion/foo"));
    }
}
