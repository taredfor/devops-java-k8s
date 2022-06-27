package org.example.framework.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
public class TokenAuthentication implements Authentication{
  private final Object principal;
  private Object credentials;
  private Collection<String> authorities;
  @Setter
  private boolean authenticated = false;

  // for request
  public TokenAuthentication(Object principal, Object credentials) {
    this.principal = principal;
    this.credentials = credentials;
  }

  // for response
  public TokenAuthentication(Object principal, Object credentials, Collection<String> authorities, boolean authenticated) {
    this.principal = principal;
    this.credentials = credentials;
    this.authorities = authorities;
    this.authenticated = authenticated;
  }

  @Override
  public void eraseCredentials() {
    credentials = null;
  }
}
