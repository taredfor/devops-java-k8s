package org.example.framework.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class AnonymousAuthentication implements Authentication {
  private final Object principal;
  private final Object credentials = null;
  private final Collection<String> authorities = List.of(Roles.ROLE_ANONYMOUS);
  private final boolean authenticated = true;

  @Override
  public void setAuthenticated(boolean authenticated) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void eraseCredentials() {
  }
}
