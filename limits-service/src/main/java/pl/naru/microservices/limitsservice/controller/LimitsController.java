package pl.naru.microservices.limitsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.naru.microservices.limitsservice.configuration.LimitsConfiguration;
import pl.naru.microservices.limitsservice.model.Limits;

@RestController
public class LimitsController {
    private final LimitsConfiguration limitsConfiguration;

    public LimitsController(LimitsConfiguration limitsConfiguration) {
        this.limitsConfiguration = limitsConfiguration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {

        return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
    }
}
