package com.example.smartcitysystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findTopByOrderByTsDesc();

}
