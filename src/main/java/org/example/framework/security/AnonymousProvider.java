package org.example.framework.security;

public interface AnonymousProvider {
  default AnonymousAuthentication provide() {
    return new AnonymousAuthentication("anonymous");
  }
}
