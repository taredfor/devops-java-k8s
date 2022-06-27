package org.example.framework.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorController {
  private ErrorController() {}

  public static void unauthorized(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    sendError(req, resp, 401, "Unauthorized");
  }

  public static void notFound(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    sendError(req, resp, 404, "Not Found");
  }

  public static void methodNotAllowed(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    sendError(req, resp, 405, "Method Not Allowed");
  }

  public static void internalServerError(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    sendError(req, resp, 500, "Internal Server Error");
  }

  private static void sendError(HttpServletRequest req, HttpServletResponse resp, int code, String message) throws IOException {
    resp.sendError(code, message);
  }
}
