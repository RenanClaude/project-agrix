package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to handle controller errors.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({FarmNotFoundException.class})
  public ResponseEntity<String> farmNotFound(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  @ExceptionHandler({CropNotFoundException.class})
  public ResponseEntity<String> cropNotFound(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  /**
   * Method to catch and handle the fertilizer not found exception.
   */
  @ExceptionHandler({FertilizerNotFoundException.class})
  public ResponseEntity<String> fertilizerNotFound(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({PersonNotFoundException.class})
  public ResponseEntity<String> personNotFound(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
