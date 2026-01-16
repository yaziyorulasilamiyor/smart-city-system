package com.example.smartcitysystem.patterns.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public abstract class NightRoutineTemplate {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    public final RoutineResult run() {
        String runId = "NR-" + Instant.now().toEpochMilli();
        log.info("[TEMPLATE] Night routine started. runId={}", runId);

        beforeAll(runId);

        lockCityAssets(runId);
        switchToNightMode(runId);
        checkSensors(runId);
        checkBankingAndParking(runId);

        String summary = afterAll(runId);

        log.info("[TEMPLATE] Night routine finished. runId={}", runId);
        return new RoutineResult(runId, summary);
    }

    protected void beforeAll(String runId) {
        log.info("[TEMPLATE] beforeAll (default). runId={}", runId);
    }

    protected abstract void lockCityAssets(String runId);

    protected void switchToNightMode(String runId) {
        log.info("[TEMPLATE] switchToNightMode (default). runId={}", runId);
    }

    protected abstract void checkSensors(String runId);

    protected void checkBankingAndParking(String runId) {
        log.info("[TEMPLATE] checkBankingAndParking (default). runId={}", runId);
    }

    protected String afterAll(String runId) {
        log.info("[TEMPLATE] afterAll (default). runId={}", runId);
        return "Default night routine OK";
    }


    public record RoutineResult(String runId, String summary) {}
}
