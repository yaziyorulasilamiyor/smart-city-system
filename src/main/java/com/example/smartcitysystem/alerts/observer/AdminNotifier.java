package com.example.smartcitysystem.alerts.observer;

import com.example.smartcitysystem.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AdminNotifier implements AlertSubscriber {

    private static final Logger log = LoggerFactory.getLogger(AdminNotifier.class);

    @Override
    public void onAlert(Alert alert) {
        log.info("[OBSERVER] Admin notified: alertId={}, type={}, severity={}, msg={}",
                alert.getId(), alert.getType(), alert.getSeverity(), alert.getMessage());
    }
}
