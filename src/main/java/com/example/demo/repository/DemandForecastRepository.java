package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DemandForecast;

public interface DemandForecastRepository
        extends JpaRepository<DemandForecast, Long> {
}
