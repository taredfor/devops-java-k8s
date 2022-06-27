package org.example.demo;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.security.NoSuchAlgorithmException;

public class PasswdDemo {
  public static void main(String[] args) {
    final var passwordEncoder = new Argon2PasswordEncoder();
    System.out.println(passwordEncoder.encode("god"));
    System.out.println(passwordEncoder.encode("qwerty123"));
  }
}
