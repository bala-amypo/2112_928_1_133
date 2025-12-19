package com.example.demo.service;

import com.example.demo.entity.DemandForecast;

public interface DemandForecastService {

    DemandForecast getForecast(Long storeId, Long productId);
}
