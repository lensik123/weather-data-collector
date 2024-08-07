package ru.baysarov.weather.data.collector.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.baysarov.weather.data.collector.DTO.MeasurementDTO;
import ru.baysarov.weather.data.collector.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

  private final SensorService sensorService;

  @Autowired
  public MeasurementValidator(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return MeasurementDTO.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    MeasurementDTO measurementDTO = (MeasurementDTO) target;
    if (sensorService.findSensorByName(measurementDTO.getSensorDTO().getName()).isEmpty()) {
      errors.rejectValue("sensorDTO.name", "","Sensor does not exist. Name: " + measurementDTO.getSensorDTO().getName());
    }

  }
}
