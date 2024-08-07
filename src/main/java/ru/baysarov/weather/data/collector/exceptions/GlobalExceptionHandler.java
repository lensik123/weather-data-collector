package ru.baysarov.weather.data.collector.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex,
      WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MeasurementNotCreatedException.class)
  public final ResponseEntity<String> handleMeasurementNotCreatedException(
      MeasurementNotCreatedException ex, WebRequest request) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
    SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
  }
}