package ru.baysarov.weather.data.collector.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baysarov.weather.data.collector.DTO.MeasurementDTO;
import ru.baysarov.weather.data.collector.DTO.MeasurementsResponse;
import ru.baysarov.weather.data.collector.DTO.SensorDTO;
import ru.baysarov.weather.data.collector.exceptions.MeasurementNotCreatedException;
import ru.baysarov.weather.data.collector.models.Measurement;
import ru.baysarov.weather.data.collector.models.Sensor;
import ru.baysarov.weather.data.collector.services.MeasurementService;
import ru.baysarov.weather.data.collector.validator.MeasurementValidator;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

  private final MeasurementService measurementService;
  private final ModelMapper modelMapper;
  private final MeasurementValidator measurementValidator;

  @Autowired
  public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper,
      MeasurementValidator measurementValidator) {
    this.measurementService = measurementService;
    this.modelMapper = modelMapper;
    this.measurementValidator = measurementValidator;
  }

  @GetMapping()
  public MeasurementsResponse getAllMeasurements() {

    MeasurementsResponse measurementsResponse =  new MeasurementsResponse(
        measurementService.findAll().stream().map(this::convertMeasurementToMeasurementDto).collect(
            Collectors.toList()));

    return measurementsResponse;

  }

  @GetMapping("/rainyDaysCount")
  public Long getRainyDays() {
    return measurementService.findAll()
        .stream()
        .filter(Measurement::isRaining)
        .count();

  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> addMeasurement(
      @RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
    measurementValidator.validate(measurementDTO, bindingResult);


    if (bindingResult.hasErrors()) {
      StringBuilder errors = new StringBuilder();
      List<FieldError> fieldErrors = bindingResult.getFieldErrors();

      for (FieldError fieldError : fieldErrors) {
        errors.append(fieldError.getDefaultMessage()).append("\n");
      }
      throw new MeasurementNotCreatedException(errors.toString());
    }

    measurementService.addMeasurement(measurementDTO);
    return ResponseEntity.ok(HttpStatus.CREATED);
  }

  private Measurement convertMeasurementDtoToMeasurement(MeasurementDTO measurementDTO) {
    return modelMapper.map(measurementDTO, Measurement.class);
  }

  private MeasurementDTO convertMeasurementToMeasurementDto(Measurement measurement) {
    MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
    measurementDTO.setSensorDTO(convertSensorToSensorDTO(measurement.getSensor()));
    return measurementDTO;
  }

  private SensorDTO convertSensorToSensorDTO(@NotNull Sensor sensor) {
    SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
    return sensorDTO;
  }

}
