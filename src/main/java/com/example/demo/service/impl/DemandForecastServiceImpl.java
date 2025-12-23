package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository forecastRepo;
    private final StoreRepository storeRepo;
    private final ProductRepository productRepo;

    public DemandForecastServiceImpl(DemandForecastRepository forecastRepo,
                                     StoreRepository storeRepo,
                                     ProductRepository productRepo) {
        this.forecastRepo = forecastRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        if (forecast.getForecastDate().isBefore(LocalDate.now().plusDays(1))) {
            throw new BadRequestException("Forecast date must be in the future");
        }
        if (forecast.getPredictedDemand() < 0) {
            throw new BadRequestException("Predicted demand must be >= 0");
        }

        Store store = storeRepo.findById(forecast.getStore().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        Product product = productRepo.findById(forecast.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        forecast.setStore(store);
        forecast.setProduct(product);

        return forecastRepo.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return forecastRepo.findByStore_Id(storeId);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return forecastRepo.findByStoreAndProductAndForecastDateAfter(
                store, product, LocalDate.now())
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Forecast not found"));
    }
}
