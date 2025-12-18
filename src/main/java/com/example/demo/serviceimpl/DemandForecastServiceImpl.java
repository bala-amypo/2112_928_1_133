package com.example.demo.service.impl;

import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Override
    public double forecastDemand(Long productId) {
        return 0.0;
    }
}
