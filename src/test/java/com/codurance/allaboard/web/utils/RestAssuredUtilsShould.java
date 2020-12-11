package com.codurance.allaboard.web.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestAssuredUtilsShould {

    @Autowired
    RestAssuredUtils utils;

    @BeforeEach
    private void setUp() {
        this.utils = new RestAssuredUtils();
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
