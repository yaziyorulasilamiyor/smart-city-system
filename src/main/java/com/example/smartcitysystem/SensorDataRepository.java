package com.example.smartcitysystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findTop10ByOrderByIdDesc();
}
