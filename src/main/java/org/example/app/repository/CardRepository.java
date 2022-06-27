package org.example.app.repository;

import lombok.RequiredArgsConstructor;
import org.example.app.domain.Card;
import org.example.jdbc.JdbcTemplate;
import org.example.jdbc.RowMapper;

import java.util.List;

@RequiredArgsConstructor
public class CardRepository {
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Card> cardRowMapper = resultSet -> new Card(
      resultSet.getLong("id"),
      resultSet.getString("number"),
      resultSet.getLong("balance")
  );

  public List<Card> getAllByOwnerId(long ownerId) {
    // language=PostgreSQL
    return jdbcTemplate.queryAll(
        "SELECT id, number, balance FROM cards WHERE \"ownerId\" = ? AND active = TRUE",
        cardRowMapper,
        ownerId
    );
  }
}
