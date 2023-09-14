package com.betrybe.agrix.exception;

/**
 * Define exceção para fertilizer não encontrada.
 */
public class FertilizerNotFound extends RuntimeException {

  public FertilizerNotFound(String message) {
    super(message);
  }
}
