package org.example.framework.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@FunctionalInterface
public interface Handler {
  void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
