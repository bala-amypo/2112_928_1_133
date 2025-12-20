package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository demandForecastRepository;

    public DemandForecastServiceImpl(DemandForecastRepository demandForecastRepository) {
        this.demandForecastRepository = demandForecastRepository;
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return demandForecastRepository.findByStore_Id(storeId);
    }
}
