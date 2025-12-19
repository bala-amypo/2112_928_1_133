package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DemandForecastRepository
        extends JpaRepository<DemandForecast, Long> {

    List<DemandForecast> findByStore_Id(Long storeId);

    List<DemandForecast> findByProduct_Id(Long productId);

    List<DemandForecast> findByStoreAndProductAndForecastDateAfter(
            com.example.demo.entity.Store store,
            com.example.demo.entity.Product product,
            LocalDate date
    );
}
