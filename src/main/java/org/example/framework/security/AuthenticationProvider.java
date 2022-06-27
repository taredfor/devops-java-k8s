package org.example.framework.security;

public interface AuthenticationProvider {
  Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
