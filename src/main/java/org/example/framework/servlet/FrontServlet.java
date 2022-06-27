package org.example.framework.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.framework.attribute.ContextAttributes;

import java.io.IOException;
import java.util.Map;

// 1. Classic Web Applications
// 2. Web Application
@Slf4j
public class FrontServlet extends HttpServlet {
  private transient Map<String, Map<String, Handler>> routes;

  @Override
  public void init() {
    routes = (Map<String, Map<String, Handler>>) getServletContext().getAttribute(ContextAttributes.ROUTES_ATTR);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      final Map<String, Handler> handlers = routes.get(getPath(req));
      if (handlers == null) {
        ErrorController.notFound(req, resp);
        return;
      }

      final Handler handler = handlers.get(req.getMethod());
      if (handler == null) {
        ErrorController.methodNotAllowed(req, resp);
        return;
      }

      handler.handle(req, resp);
    } catch (Exception e) {
      log.warn("request handling exception", e);
      ErrorController.internalServerError(req, resp);
    }
  }

  private String getPath(HttpServletRequest req) {
    return req.getRequestURI().substring(req.getContextPath().length());
  }
}
