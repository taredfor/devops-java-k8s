package org.example.app.repository;

import lombok.RequiredArgsConstructor;
import org.example.app.domain.User;
import org.example.app.domain.UserWithPassword;
import org.example.jdbc.JdbcTemplate;
import org.example.jdbc.RowMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepository {
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<User> rowMapper = resultSet -> new User(
      resultSet.getLong("id"),
      resultSet.getString("username")
  );
  private final RowMapper<UserWithPassword> rowMapperWithPassword = resultSet -> new UserWithPassword(
      resultSet.getLong("id"),
      resultSet.getString("username"),
      resultSet.getString("password")
  );

  public Optional<User> getByUsername(String username) {
    // language=PostgreSQL
    return jdbcTemplate.queryOne("SELECT id, username FROM users WHERE username = ?", rowMapper, username);
  }

  public Optional<UserWithPassword> getByUsernameWithPassword(String username) {
    // language=PostgreSQL
    return jdbcTemplate.queryOne("SELECT id, username, password FROM users WHERE username = ?", rowMapperWithPassword, username);
  }

  /**
   * saves user to db
   *
   * @param id - user id, if 0 - insert, if not 0 - update
   * @param username
   * @param hash
   */
  // TODO: DuplicateKeyException <-
  public Optional<User> save(long id, String username, String hash) {
    // language=PostgreSQL
    return id == 0 ? jdbcTemplate.queryOne(
        """
            INSERT INTO users(username, password) VALUES (?, ?) RETURNING id, username
            """,
        rowMapper,
        username, hash
    ) : jdbcTemplate.queryOne(
        """
            UPDATE users SET username = ?, password = ? WHERE id = ? RETURNING id, username
            """,
        rowMapper,
        username, hash, id
    );
  }

  public Optional<User> findByToken(String token) {
    // language=PostgreSQL
    return jdbcTemplate.queryOne(
        """
            SELECT u.id, u.username FROM tokens t
            JOIN users u ON t."userId" = u.id
            WHERE t.token = ?
            """,
        rowMapper,
        token
    );
  }

  public void saveToken(long userId, String token) {
    // query - SELECT'ов (ResultSet)
    // update - ? int/long
    // language=PostgreSQL
    jdbcTemplate.update(
        """
        INSERT INTO tokens(token, "userId") VALUES (?, ?)
        """,
        token, userId
    );
  }
}
