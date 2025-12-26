package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Autowired
    private DemandForecastRepository demandForecastRepository;

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {

        if (forecast.getForecastDate() == null ||
                forecast.getForecastDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Forecast date must be in the future");
        }

        return demandForecastRepository.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return demandForecastRepository.findByStore_Id(storeId);
    }

    @Override
    public List<DemandForecast> getForecastsForProduct(Long productId) {
        return demandForecastRepository.findByProduct_Id(productId);
    }
}
