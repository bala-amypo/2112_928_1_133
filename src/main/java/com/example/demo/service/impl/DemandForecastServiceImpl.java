package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository forecastRepository;

    public DemandForecastServiceImpl(DemandForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        if (forecast.getForecastDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Forecast date must be in the future");
        }
        if (forecast.getPredictedDemand() < 0) {
            throw new BadRequestException("Predicted demand must be >= 0");
        }
        return forecastRepository.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return forecastRepository.findByStore_Id(storeId);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {
        return forecastRepository.findAll().stream()
                .filter(f -> f.getStore().getId().equals(storeId)
                        && f.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));
    }
}
