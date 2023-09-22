package com.betrybe.agrix.exception;

/**
 * Exception for crop not found.
 */
public class CropNotFoundException extends RuntimeException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
