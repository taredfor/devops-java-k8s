package org.example.app.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.app.dto.LoginRequestDto;
import org.example.app.service.UserService;

import java.io.IOException;

@RequiredArgsConstructor
public class UserHandler {
  private final UserService service;
  private final Gson gson;

  public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final var requestDto = gson.fromJson(req.getReader(), LoginRequestDto.class);
    final var responseDto = service.login(requestDto);
    resp.setHeader("Content-Type", "application/json");
    resp.getWriter().write(gson.toJson(responseDto));
  }
}
