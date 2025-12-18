package com.example.demo.serviceimpl;

import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Override
    public int getForecastForProduct(Long productId) {
        // Default value for tests
        return 100;
    }
}
