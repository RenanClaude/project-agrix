package com.betrybe.agrix.exception;

/**
 * Exception for fertilizer not found.
 */
public class FertilizerNotFoundException extends RuntimeException {

  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
