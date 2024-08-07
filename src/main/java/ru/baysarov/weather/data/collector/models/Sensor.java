package ru.baysarov.weather.data.collector.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Sensor")
@Data
public class Sensor {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  @NotEmpty(message = "Name shouldn't be empty")
  @Size(min = 3, max = 20, message = "Character amount should be between 3 and 20")
  private String name;



}
