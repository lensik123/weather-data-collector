package ru.baysarov.weather.data.collector.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDTO {

  @Column(name = "value")
  @Min(value = -100, message = "Value should be at least -100")
  @Max(value = 100, message = "Value should be at most 100")
  private double value;

  @NotNull
  private boolean raining;

  @JsonProperty("sensor")
  private SensorDTO sensorDTO;

}

