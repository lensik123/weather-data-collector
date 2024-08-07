package ru.baysarov.weather.data.collector.services;

import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baysarov.weather.data.collector.DTO.MeasurementDTO;
import ru.baysarov.weather.data.collector.exceptions.SensorNotFoundException;
import ru.baysarov.weather.data.collector.models.Measurement;
import ru.baysarov.weather.data.collector.models.Sensor;
import ru.baysarov.weather.data.collector.repositories.MeasurementRepository;
import ru.baysarov.weather.data.collector.exceptions.MeasurementNotFoundException;

@Service
@Transactional
public class MeasurementService {

  private final MeasurementRepository measurementRepository;
  private final SensorService sensorService;
  private final ModelMapper modelMapper;
  @Autowired
  public MeasurementService(MeasurementRepository measurementRepository,
      SensorService sensorService, ModelMapper modelMapper) {
    this.measurementRepository = measurementRepository;
    this.sensorService = sensorService;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public void save(Measurement measurement) {
    measurementRepository.save(measurement);
  }

  public Measurement findOne(long id) {
    return measurementRepository.findById(id).orElseThrow(MeasurementNotFoundException::new);
  }

  @Transactional
  public void addMeasurement(MeasurementDTO measurementDTO) {
    Measurement measurement = convertMeasurementDtoToMeasurement(measurementDTO);
    Sensor sensor = sensorService.findSensorByName(measurementDTO.getSensorDTO().getName())
        .orElseThrow(SensorNotFoundException::new);

    measurement.setSensor(sensor);
    measurement.setEnqTime(LocalDateTime.now());
    measurementRepository.save(measurement);
  }

  private Measurement convertMeasurementDtoToMeasurement(MeasurementDTO measurementDTO) {
    return modelMapper.map(measurementDTO, Measurement.class);
  }



  public List<Measurement> findAll() {

    return measurementRepository.findAll();

  }
}
