package com.betrybe.agrix.exception;

/**
 * Exception for farm not found.
 */
public class FarmNotFoundException extends RuntimeException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
