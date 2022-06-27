package org.example.framework.listener;

import com.google.gson.Gson;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.ServletSecurity;
import lombok.extern.slf4j.Slf4j;
import org.example.app.handler.CardHandler;
import org.example.app.handler.UserHandler;
import org.example.app.repository.CardRepository;
import org.example.app.repository.UserRepository;
import org.example.app.service.CardService;
import org.example.app.service.UserService;
import org.example.framework.attribute.ContextAttributes;
import org.example.framework.servlet.Handler;
import org.example.jdbc.JdbcTemplate;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.Map;

import static org.example.framework.http.Methods.GET;
import static org.example.framework.http.Methods.POST;

@Slf4j
public class ServletContextLoadDestroyListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContextListener.super.contextInitialized(sce);
    try {
      final var context = sce.getServletContext();

      final var initialContext = new InitialContext();
      final var dataSource = ((DataSource) initialContext.lookup("java:/comp/env/jdbc/db"));
      final var jdbcTemplate = new JdbcTemplate(dataSource);
      context.setAttribute(ContextAttributes.JDBC_TEMPLATE_ATTR, jdbcTemplate);

      final var gson = new Gson();

      final var userRepository = new UserRepository(jdbcTemplate);
      final var passwordEncoder = new Argon2PasswordEncoder();
      final var keyGenerator = new Base64StringKeyGenerator(64);
      final var userService = new UserService(userRepository, passwordEncoder, keyGenerator);
      context.setAttribute(ContextAttributes.AUTH_PROVIDER_ATTR, userService);
      context.setAttribute(ContextAttributes.ANON_PROVIDER_ATTR, userService);
      final var userHandler = new UserHandler(userService, gson);

      final var cardRepository = new CardRepository(jdbcTemplate);
      final var cardService = new CardService(cardRepository);
      final var cardHandler = new CardHandler(cardService, gson);

      final var routes = Map.<String, Map<String, Handler>>of(
          "/cards.getAll", Map.of(GET, cardHandler::getAll),
          "/users.login", Map.of(POST, userHandler::login)
      );
      context.setAttribute(ContextAttributes.ROUTES_ATTR, routes);

    } catch (Exception e) {
      log.error("servlet context initialization exception", e);
      throw new ContextInitializationException(e);
    }
  }
}
