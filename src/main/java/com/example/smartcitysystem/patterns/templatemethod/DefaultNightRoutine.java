package com.example.smartcitysystem.patterns.templatemethod;

import org.springframework.stereotype.Component;

@Component
public class DefaultNightRoutine extends NightRoutineTemplate {

    @Override
    protected void lockCityAssets(String runId) {
        log.info("[TEMPLATE][DEFAULT] lockCityAssets: public lighting schedule locked. runId={}", runId);
    }

    @Override
    protected void checkSensors(String runId) {
        log.info("[TEMPLATE][DEFAULT] checkSensors: last sensor snapshot checked. runId={}", runId);
    }

    @Override
    protected String afterAll(String runId) {
        log.info("[TEMPLATE][DEFAULT] afterAll: report generated. runId={}", runId);
        return "NightRoutine(Default) completed + report generated";
    }
}
