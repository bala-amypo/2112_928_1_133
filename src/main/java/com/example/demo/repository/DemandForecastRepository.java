package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {

    @Query("FROM DemandForecast d WHERE d.store.id = :storeId")
    List<DemandForecast> findForecastsByStore(@Param("storeId") Long storeId);
}
