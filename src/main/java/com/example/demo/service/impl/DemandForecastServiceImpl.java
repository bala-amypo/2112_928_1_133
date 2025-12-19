package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository forecastRepository;

    public DemandForecastServiceImpl(DemandForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {

        List<DemandForecast> forecasts = forecastRepository.findAll();

        return forecasts.stream()
                .filter(f ->
                        f.getStore().getId().equals(storeId) &&
                        f.getProduct().getId().equals(productId)
                )
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Forecast not found")
                );
    }
}
