package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {

    // ✅ REQUIRED by existing services/tests
    List<DemandForecast> findByStore_Id(Long storeId);

    List<DemandForecast> findByStoreAndProductAndForecastDateAfter(
            Store store, Product product, LocalDate date);

    // ✅ HQL
    @Query("FROM DemandForecast d WHERE d.store.id = :storeId")
    List<DemandForecast> findForecastsByStore(@Param("storeId") Long storeId);
}
