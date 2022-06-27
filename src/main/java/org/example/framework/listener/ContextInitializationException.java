package org.example.framework.listener;

public class ContextInitializationException extends RuntimeException {
  public ContextInitializationException() {
  }

  public ContextInitializationException(String message) {
    super(message);
  }

  public ContextInitializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ContextInitializationException(Throwable cause) {
    super(cause);
  }

  public ContextInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
