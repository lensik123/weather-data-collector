package ru.baysarov.weather.data.collector.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "measurement")
public class Measurement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "value")
  @Min(value = -100, message = "Value should be at least -100")
  @Max(value = 100, message = "Value should be at most 100")
  private double value;

  @NotNull
  @Column(name = "raining")
  private boolean raining;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "sensor", referencedColumnName = "name")
  private Sensor sensor;

  @Column(name = "enq_time")
  private LocalDateTime enqTime;
}
