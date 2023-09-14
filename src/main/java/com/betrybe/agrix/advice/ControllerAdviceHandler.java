package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFound;
import com.betrybe.agrix.exception.FarmNotFound;
import com.betrybe.agrix.exception.FertilizerNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Define controller advice.
 */
@ControllerAdvice
public class ControllerAdviceHandler {

  @ExceptionHandler(FarmNotFound.class)
  public ResponseEntity<String> handleFarmNotFound(Exception e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }

  @ExceptionHandler(CropNotFound.class)
  public ResponseEntity<String> handleCropNotFound(Exception e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }

  @ExceptionHandler(FertilizerNotFound.class)
  public ResponseEntity<String> handleFertilizerNotFound(Exception e) {
    return ResponseEntity.status(404).body(e.getMessage());
  }
}
