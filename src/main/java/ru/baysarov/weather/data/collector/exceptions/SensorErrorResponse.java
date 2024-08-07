package ru.baysarov.weather.data.collector.exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorErrorResponse {
  private String errorMessage;
  private LocalDateTime timeOfError;

}
