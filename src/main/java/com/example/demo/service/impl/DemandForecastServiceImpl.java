package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public DemandForecastServiceImpl(
            DemandForecastRepository repository,
            StoreRepository storeRepository,
            ProductRepository productRepository) {

        this.repository = repository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        return repository.save(forecast);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (store == null || product == null) {
            return null;
        }

        return repository.findByStoreAndProduct(store, product).orElse(null);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return repository.findByStore_Id(storeId);
    }
}
