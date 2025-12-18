package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.DemandForecastService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository forecastRepo;
    private final StoreRepository storeRepo;
    private final ProductRepository productRepo;

    public DemandForecastServiceImpl(
            DemandForecastRepository forecastRepo,
            StoreRepository storeRepo,
            ProductRepository productRepo) {
        this.forecastRepo = forecastRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {

        if (!forecast.getForecastDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Forecast date must be in the future");
        }

        if (forecast.getPredictedDemand() < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        return forecastRepo.save(forecast);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {

        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return forecastRepo
                .findByStoreAndProductAndForecastDateAfter(store, product, LocalDate.now())
                .stream()
                .findFirst()
                .orElseThrow(() -> new BadRequestException("No forecast found"));
    }
}
