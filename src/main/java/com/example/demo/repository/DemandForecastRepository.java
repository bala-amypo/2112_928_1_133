package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {

    Optional<DemandForecast> findByStoreAndProduct(Store store, Product product);
}
