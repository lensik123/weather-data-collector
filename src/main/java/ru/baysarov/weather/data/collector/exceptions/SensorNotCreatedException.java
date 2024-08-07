package ru.baysarov.weather.data.collector.exceptions;

public class SensorNotCreatedException extends RuntimeException {
  public SensorNotCreatedException(String message) {
    super(message);
  }
}