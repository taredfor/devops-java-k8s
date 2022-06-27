package org.example.app.util;

import jakarta.servlet.http.HttpServletRequest;
import org.example.app.domain.User;
import org.example.framework.attribute.RequestAttributes;
import org.example.framework.security.Authentication;

public class UserHelper {
  private UserHelper() {
  }

  public static User getUser(HttpServletRequest req) {
    return (User) ((Authentication) req.getAttribute(RequestAttributes.AUTH_ATTR)).getPrincipal();
  }
}
