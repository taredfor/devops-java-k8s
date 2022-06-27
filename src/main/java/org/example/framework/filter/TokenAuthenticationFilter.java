package org.example.framework.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.framework.attribute.ContextAttributes;
import org.example.framework.attribute.RequestAttributes;
import org.example.framework.security.*;
import org.example.framework.servlet.ErrorController;

import java.io.IOException;

public class TokenAuthenticationFilter extends HttpFilter {
  private transient AuthenticationProvider provider;

  @Override
  public void init() throws ServletException {
    provider = (AuthenticationProvider) getServletContext().getAttribute(ContextAttributes.AUTH_PROVIDER_ATTR);
  }

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    if (!authenticationIsRequired(req)) {
      super.doFilter(req, res, chain);
      return;
    }

    final var token = req.getHeader("Authorization");
    if (token == null) {
      super.doFilter(req, res, chain);
      return;
    }

    try {
      final var authentication = provider.authenticate(new TokenAuthentication(token, null));
      req.setAttribute(RequestAttributes.AUTH_ATTR, authentication);
    } catch (AuthenticationException e) {
      ErrorController.unauthorized(req, res);
      return;
    }

    super.doFilter(req, res, chain);
  }

  private boolean authenticationIsRequired(HttpServletRequest req) {
    final var existingAuth = (Authentication) req.getAttribute(RequestAttributes.AUTH_ATTR);

    if (existingAuth == null || !existingAuth.isAuthenticated()) {
      return true;
    }

    return AnonymousAuthentication.class.isAssignableFrom(existingAuth.getClass());
  }
}
