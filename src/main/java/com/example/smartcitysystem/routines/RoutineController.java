package com.example.smartcitysystem.routines;

import com.example.smartcitysystem.patterns.templatemethod.DefaultNightRoutine;
import com.example.smartcitysystem.patterns.templatemethod.NightRoutineTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/routines")
public class RoutineController {

    private final DefaultNightRoutine defaultNightRoutine;

    public RoutineController(DefaultNightRoutine defaultNightRoutine) {
        this.defaultNightRoutine = defaultNightRoutine;
    }

    @PostMapping("/night/run")
    public Map<String, Object> runNightRoutine() {
        NightRoutineTemplate.RoutineResult res = defaultNightRoutine.run();
        return Map.of(
                "ok", true,
                "pattern", "TEMPLATE_METHOD",
                "runId", res.runId(),
                "summary", res.summary()
        );
    }
}
