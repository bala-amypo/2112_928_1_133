package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Override
    public DemandForecast getForecastForProduct(Long productId) {
        // Dummy forecast for test cases
        return new DemandForecast(productId, 100);
    }
}
