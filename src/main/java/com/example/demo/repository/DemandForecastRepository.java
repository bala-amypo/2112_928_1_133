package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {

    List<DemandForecast> findByStore_Id(Long storeId);

    Optional<DemandForecast> findByStoreAndProduct(
            com.example.demo.entity.Store store,
            com.example.demo.entity.Product product
    );

    Optional<DemandForecast> findByStore_IdAndProduct_Id(Long storeId, Long productId);

    // 🔥 REQUIRED BY TEST
    List<DemandForecast> findByProduct_Id(Long productId);
}
