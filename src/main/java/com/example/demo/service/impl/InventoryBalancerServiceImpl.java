package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository transferRepo;
    private final InventoryLevelRepository inventoryRepo;
    private final DemandForecastRepository forecastRepo;
    private final StoreRepository storeRepo;

    public InventoryBalancerServiceImpl(
            TransferSuggestionRepository transferRepo,
            InventoryLevelRepository inventoryRepo,
            DemandForecastRepository forecastRepo,
            StoreRepository storeRepo) {
        this.transferRepo = transferRepo;
        this.inventoryRepo = inventoryRepo;
        this.forecastRepo = forecastRepo;
        this.storeRepo = storeRepo;
    }

    @Override
    public void generateSuggestions(Long productId) {

        List<Store> stores = storeRepo.findAll();

        for (Store source : stores) {
            for (Store target : stores) {

                if (source.getId().equals(target.getId())) continue;

                inventoryRepo.findByStoreAndProduct(source,
                        new Product() {{ setId(productId); }})
                        .ifPresent(inv -> {

                            List<DemandForecast> forecasts =
                                    forecastRepo.findByStoreAndProductAndForecastDateAfter(
                                            target,
                                            inv.getProduct(),
                                            LocalDate.now());

                            if (forecasts.isEmpty()) {
                                throw new BadRequestException("No forecast found");
                            }

                            DemandForecast df = forecasts.get(0);

                            if (inv.getQuantity() > df.getPredictedDemand()) {

                                TransferSuggestion ts = new TransferSuggestion();
                                ts.setSourceStore(source);
                                ts.setTargetStore(target);
                                ts.setProduct(inv.getProduct());
                                ts.setQuantity(inv.getQuantity() - df.getPredictedDemand());
                                ts.setPriority("HIGH");

                                transferRepo.save(ts);
                            }
                        });
            }
        }
    }
}
