package com.example.demo.controller;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecasts")
public class DemandForecastController {

    @Autowired
    private DemandForecastService demandForecastService;

  
    @PostMapping
    public DemandForecast createForecast(@RequestBody DemandForecast forecast) {
        return demandForecastService.createForecast(forecast);
    }

    @GetMapping("/store/{storeId}")
    public List<DemandForecast> getForecastsForStore(@PathVariable Long storeId) {
        return demandForecastService.getForecastsForStore(storeId);
    }

    
    @GetMapping("/product/{productId}")
    public List<DemandForecast> getForecastsForProduct(@PathVariable Long productId) {
        return demandForecastService.getForecastsForProduct(productId);
    }
}
