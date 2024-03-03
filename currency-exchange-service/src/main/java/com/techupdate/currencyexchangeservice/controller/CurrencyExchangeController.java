package com.techupdate.currencyexchangeservice.controller;

import com.techupdate.currencyexchangeservice.entity.CurrencyExchange;
import com.techupdate.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private final CurrencyExchangeRepository exchangeRepository;
    private final Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository exchangeRepository, Environment environment) {
        this.exchangeRepository = exchangeRepository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {

        CurrencyExchange exchange = exchangeRepository.findByFromAndTo(from, to);

        if (exchange == null) {
            throw new RuntimeException("unable to find data for " + from + " to " + to);
        }
        exchange.setEnvironment(environment.getProperty("local.server.port"));

        return exchange;
    }
}
