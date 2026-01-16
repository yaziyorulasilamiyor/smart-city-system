package com.example.smartcitysystem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Value("${smartcity.rules.traffic-threshold}")
    private int trafficThreshold;
    private final SensorDataRepository repo;
    private final AlertRepository alertRepo;
    private final com.example.smartcitysystem.alerts.observer.AlertPublisher alertPublisher;


    public SensorController(
            SensorDataRepository repo,
            AlertRepository alertRepo,
            com.example.smartcitysystem.alerts.observer.AlertPublisher alertPublisher
    ) {
        this.repo = repo;
        this.alertRepo = alertRepo;
        this.alertPublisher = alertPublisher;
    }

    @PostMapping("/ingest")
    public String ingest(@Valid @RequestBody SensorRequest request) {
        repo.save(new SensorData(request.getType(), request.getValue(), Instant.now()));

        if ("traffic".equalsIgnoreCase(request.getType())
                && request.getValue() > trafficThreshold) {
            Alert saved = alertRepo.save(new Alert(
                    "traffic",
                    "Congestion Alert: traffic=" + request.getValue(),
                    "HIGH",
                    Instant.now()
            ));

            alertPublisher.publish(saved);
        }

        return "sensor data received";
    }

    @GetMapping("/latest")
    public List<SensorData> latest() {
        return repo.findTop10ByOrderByIdDesc();
    }

}
