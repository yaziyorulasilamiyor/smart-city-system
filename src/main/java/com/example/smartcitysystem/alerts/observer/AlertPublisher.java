package com.example.smartcitysystem.alerts.observer;

import com.example.smartcitysystem.Alert;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlertPublisher {

    private final List<AlertSubscriber> subscribers;

    public AlertPublisher(List<AlertSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void publish(Alert alert) {
        for (AlertSubscriber sub : subscribers) {
            sub.onAlert(alert);
        }
    }
}
