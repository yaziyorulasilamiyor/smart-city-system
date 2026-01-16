package com.example.smartcitysystem.alerts.observer;

import com.example.smartcitysystem.Alert;

public interface AlertSubscriber {
    void onAlert(Alert alert);
}
