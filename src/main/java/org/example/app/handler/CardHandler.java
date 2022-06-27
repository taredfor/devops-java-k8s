package org.example.app.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.app.service.CardService;
import org.example.app.util.UserHelper;

import java.io.IOException;

@RequiredArgsConstructor
public class CardHandler { // Servlet -> Controller -> Service (domain) -> domain
  private final CardService service;
  private final Gson gson;

  public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // cards.getAll?ownerId=1
    final var user = UserHelper.getUser(req);
    final var data = service.getAllByOwnerId(user.getId());
    resp.setHeader("Content-Type", "application/json");
    resp.getWriter().write(gson.toJson(data));
  }
}
