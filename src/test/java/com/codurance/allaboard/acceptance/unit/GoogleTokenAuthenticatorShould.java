package com.codurance.allaboard.acceptance.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.codurance.allaboard.config.GoogleTokenAuthenticator;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GoogleTokenAuthenticatorShould {

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private Object handler;

  @Mock
  private GoogleIdTokenVerifier googleIdTokenVerifier;

  private final String INVALID_TOKEN = "";
  private final String VALID_TOKEN = "VALID_TOKEN";

  @Test
  void deny_request_without_authorization() throws GeneralSecurityException, IOException {
    given(request.getHeader("Authorization"))
        .willReturn(INVALID_TOKEN);

    given(googleIdTokenVerifier.verify(anyString()))
        .willThrow(GeneralSecurityException.class);

    GoogleTokenAuthenticator authenticator = new TestableGoogleTokenAuthenticator();

    assertThat(authenticator.preHandle(request, response, handler), is(false));
  }

  @Test
  void accept_request_with_authorization() {
    given(request.getHeader("Authorization"))
        .willReturn(VALID_TOKEN);

    GoogleTokenAuthenticator authenticator = new TestableGoogleTokenAuthenticator();

    assertThat(authenticator.preHandle(request, response, handler), is(true));
  }

  class TestableGoogleTokenAuthenticator extends GoogleTokenAuthenticator {

    @Override
    protected GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
      return googleIdTokenVerifier;
    }
  }
}
