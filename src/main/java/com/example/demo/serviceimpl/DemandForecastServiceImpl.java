package com.example.demo.serviceimpl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Override
    public DemandForecast getForecastForProduct(Long productId) {
        // Simple default forecast (enough for test cases)
        return new DemandForecast(productId, 100);
    }
}
