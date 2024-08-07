package ru.baysarov.weather.data.collector.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementsResponse {

  private List<MeasurementDTO> measurements;

}
