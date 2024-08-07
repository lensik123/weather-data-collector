package ru.baysarov.weather.data.collector.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.baysarov.weather.data.collector.DTO.SensorDTO;
import ru.baysarov.weather.data.collector.services.SensorService;

@Component
public class SensorValidator implements Validator {

  private final SensorService sensorService;

  public SensorValidator(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SensorDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    SensorDTO sensorDTO = (SensorDTO) target;
    if (sensorService.findSensorByName(sensorDTO.getName()).isPresent()) {
      errors.rejectValue("name", "", "Sensor with this name already exists");
    }
  }
}
