package ru.baysarov.weather.data.collector.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baysarov.weather.data.collector.models.Sensor;
import ru.baysarov.weather.data.collector.repositories.SensorRepository;
import ru.baysarov.weather.data.collector.exceptions.SensorNotFoundException;

@Service
@Transactional
public class SensorService {

  private final SensorRepository sensorRepository;

  @Autowired
  public SensorService(SensorRepository sensorRepository) {
    this.sensorRepository = sensorRepository;
  }

  public Sensor findOne(int id){
    Optional<Sensor> sensor = sensorRepository.findById(id);
    return sensor.orElseThrow(SensorNotFoundException::new);
  }
  
  public Optional<Sensor> findSensorByName(String name){
    return sensorRepository.findByName(name);
  }

  @Transactional
  public void save(Sensor sensor) {
    sensorRepository.save(sensor);
  }
}
