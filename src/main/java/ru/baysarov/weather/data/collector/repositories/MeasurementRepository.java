package ru.baysarov.weather.data.collector.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baysarov.weather.data.collector.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}
