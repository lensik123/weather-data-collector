package ru.baysarov.weather.data.collector.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDTO {

  @NotEmpty(message = "Name shouldn't be empty")
  @Size(min = 3, max = 20, message = "Character amount should be between 3 and 20")
  private String name;


}

