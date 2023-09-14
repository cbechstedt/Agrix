package com.betrybe.agrix.exception;

/**
 * Define exceção para fazendo não encontrada.
 */
public class FarmNotFound extends RuntimeException {

  public FarmNotFound(String message) {
    super(message);
  }
}
