package org.example.demo;

import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;

public class TokenDemo {
  public static void main(String[] args) {
    final var keyGenerator = new Base64StringKeyGenerator(64);
    System.out.println(keyGenerator.generateKey());
  }
}
