package ru.baysarov.weather.data.collector.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baysarov.weather.data.collector.DTO.SensorDTO;
import ru.baysarov.weather.data.collector.exceptions.SensorNotCreatedException;
import ru.baysarov.weather.data.collector.models.Sensor;
import ru.baysarov.weather.data.collector.services.SensorService;
import ru.baysarov.weather.data.collector.validator.SensorValidator;

@RestController
@RequestMapping("/sensor")
public class SensorsController {

  private final ModelMapper modelMapper;
  private final SensorService sensorService;
  private final SensorValidator sensorValidator;

  @Autowired
  private SensorsController(ModelMapper modelMapper, SensorService sensorService,
      SensorValidator sensorValidator) {
    this.modelMapper = modelMapper;
    this.sensorService = sensorService;
    this.sensorValidator = sensorValidator;
  }


  @PostMapping("/registration")
  private ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
      BindingResult bindingResult) {
    sensorValidator.validate(sensorDTO, bindingResult);

    if (bindingResult.hasErrors()) {
      StringBuilder errors = new StringBuilder();
      List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

      for (FieldError fieldError : fieldErrorList) {
        errors.append(fieldError.getDefaultMessage()).append("; ");
      }
      throw new SensorNotCreatedException(errors.toString().trim());
    }
    sensorService.save(convertSensorDTOToSensor(sensorDTO));
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  private Sensor convertSensorDTOToSensor(SensorDTO sensorDTO) {
    return modelMapper.map(sensorDTO, Sensor.class);
  }


}
