package com.example.demo.controller;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecasts")
public class DemandForecastController {

    private final DemandForecastService service;

    public DemandForecastController(DemandForecastService service) {
        this.service = service;
    }

    @PostMapping
    public DemandForecast create(@RequestBody DemandForecast forecast) {
        return service.createForecast(forecast);
    }

    @GetMapping("/store/{storeId}/product/{productId}")
    public DemandForecast get(@PathVariable Long storeId,
                              @PathVariable Long productId) {
        return service.getForecast(storeId, productId);
    }
}
