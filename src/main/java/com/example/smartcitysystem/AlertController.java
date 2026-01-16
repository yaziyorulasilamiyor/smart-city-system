package com.example.smartcitysystem;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertRepository repo;

    public AlertController(AlertRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Object list() {
        return repo.findAll();
    }

    @GetMapping("/latest")
    public Alert latest() {
        return repo.findTopByOrderByTsDesc();
    }
}
