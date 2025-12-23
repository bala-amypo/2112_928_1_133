package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository suggestionRepo;
    private final InventoryLevelRepository inventoryRepo;
    private final DemandForecastRepository forecastRepo;
    private final StoreRepository storeRepo;

    public InventoryBalancerServiceImpl(
            TransferSuggestionRepository suggestionRepo,
            InventoryLevelRepository inventoryRepo,
            DemandForecastRepository forecastRepo,
            StoreRepository storeRepo) {
        this.suggestionRepo = suggestionRepo;
        this.inventoryRepo = inventoryRepo;
        this.forecastRepo = forecastRepo;
        this.storeRepo = storeRepo;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {
        List<InventoryLevel> inventories = inventoryRepo.findByProduct_Id(productId);
        if (inventories.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        Product product = inventories.get(0).getProduct();
        if (!product.getActive()) {
            throw new BadRequestException("Product is inactive");
        }

        List<TransferSuggestion> results = new ArrayList<>();

        for (InventoryLevel inv : inventories) {
            List<DemandForecast> forecasts =
                    forecastRepo.findByStoreAndProductAndForecastDateAfter(
                            inv.getStore(), product, LocalDate.now());

            if (forecasts.isEmpty()) {
                continue;
            }

            int demand = forecasts.get(0).getPredictedDemand();
            int diff = inv.getQuantity() - demand;

            if (diff > 10) {
                for (InventoryLevel targetInv : inventories) {
                    if (targetInv.getQuantity() < demand) {
                        TransferSuggestion ts = new TransferSuggestion();
                        ts.setSourceStore(inv.getStore());
                        ts.setTargetStore(targetInv.getStore());
                        ts.setProduct(product);
                        ts.setQuantity(5);
                        ts.setPriority("MEDIUM");
                        results.add(suggestionRepo.save(ts));
                        break;
                    }
                }
            }
        }

        if (results.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        return results;
    }

    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return suggestionRepo.findBySourceStoreId(storeId);
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return suggestionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
