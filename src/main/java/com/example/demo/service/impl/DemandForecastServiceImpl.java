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

        if (forecast.getForecastedDemand() < 0) {
            throw new BadRequestException("Forecasted demand must be >= 0");
        }

        if (forecast.getForecastDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Forecast date must be in the future");
        }

        return forecastRepository.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return forecastRepository.findByStore_Id(storeId);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {
        return forecastRepository.findByStore_Id(storeId).stream()
                .filter(f -> f.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Forecast not found"));
    }
}
