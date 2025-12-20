package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repository;

    public DemandForecastServiceImpl(DemandForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        return repository.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return repository.findByStore_Id(storeId);
    }
}
