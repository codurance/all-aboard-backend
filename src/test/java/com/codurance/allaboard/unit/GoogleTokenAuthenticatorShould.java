package com.codurance.allaboard.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.codurance.allaboard.config.security.GoogleTokenAuthenticator;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class GoogleTokenAuthenticatorShould {

  private final String email = "user@codurance.com";

  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private Object handler;

  @Mock
  private GoogleIdTokenVerifier googleIdTokenVerifier;

  @Mock
  private GoogleIdToken googleIdToken;

  @Mock
  private Payload payload;

  @Test
  void deny_request_without_authorization() throws GeneralSecurityException, IOException {
    String INVALID_TOKEN = "";

    request = mock(HttpServletRequest.class);
    given(request.getHeader("Authorization"))
        .willReturn(INVALID_TOKEN);

    given(googleIdTokenVerifier.verify(anyString()))
        .willThrow(GeneralSecurityException.class);

    GoogleTokenAuthenticator authenticator = new TestableGoogleTokenAuthenticator();

    assertThat(authenticator.preHandle(request, response, handler), is(false));
  }

  @Test
  void accept_request_with_authorization() throws GeneralSecurityException, IOException {
    GoogleTokenAuthenticator authenticator = new TestableGoogleTokenAuthenticator();

    request = new ValidTokenHttpServletRequest();

    given(googleIdTokenVerifier.verify(anyString()))
        .willReturn(googleIdToken);

    given(googleIdToken.getPayload())
        .willReturn(payload);

    given(payload.getEmail())
        .willReturn(email);

    assertThat(authenticator.preHandle(request, response, handler), is(true));
  }

  class TestableGoogleTokenAuthenticator extends GoogleTokenAuthenticator {

    @Override
    protected GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
      return googleIdTokenVerifier;
    }
  }

  @Test
  void set_email_in_request_header() throws GeneralSecurityException, IOException {
    GoogleTokenAuthenticator authenticator = new TestableGoogleTokenAuthenticator();

    request = new ValidTokenHttpServletRequest();

    given(googleIdTokenVerifier.verify(anyString()))
        .willReturn(googleIdToken);

    given(googleIdToken.getPayload())
        .willReturn(payload);

    given(payload.getEmail())
        .willReturn(email);

    assertThat(request.getAttribute("user_email"), is(nullValue()));

    authenticator.preHandle(request, response, handler);

    assertThat(request.getAttribute("user_email"), is(email));
  }

  class ValidTokenHttpServletRequest extends MockHttpServletRequest {

    @Override
    public String getHeader(String name) {
      return "some valid token";
    }
  }
}
