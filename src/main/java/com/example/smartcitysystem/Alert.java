package com.example.smartcitysystem;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String message;
    private String severity;
    private Instant ts;

    public Alert() {}

    public Alert(String type, String message, String severity, Instant ts) {
        this.type = type;
        this.message = message;
        this.severity = severity;
        this.ts = ts;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getMessage() { return message; }
    public String getSeverity() { return severity; }
    public Instant getTs() { return ts; }
}
