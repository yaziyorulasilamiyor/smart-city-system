package com.example.smartcitysystem;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double value;

    private Instant ts;

    public SensorData() {}

    public SensorData(String type, double value, Instant ts) {
        this.type = type;
        this.value = value;
        this.ts = ts;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }
}
