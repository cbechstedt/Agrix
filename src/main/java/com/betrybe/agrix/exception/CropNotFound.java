package com.betrybe.agrix.exception;

/**
 * Define exceção para plantação não encontrada.
 */
public class CropNotFound extends RuntimeException {

  public CropNotFound(String message) {
    super(message);
  }
}
