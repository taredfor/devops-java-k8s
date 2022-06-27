package org.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.framework.security.Authentication;

@AllArgsConstructor
@Data
public class User {
  private long id;
  private String username;
}
