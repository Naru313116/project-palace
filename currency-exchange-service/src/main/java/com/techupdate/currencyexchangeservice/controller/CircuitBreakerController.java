package com.techupdate.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    static int i = 1;
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi() {
        logger.info("Sample api: {}", i++);
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/not-existing-url", String.class);
//        return forEntity.getBody();
        return "sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
