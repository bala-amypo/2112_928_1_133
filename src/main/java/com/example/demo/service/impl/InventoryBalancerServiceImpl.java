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

        List<InventoryLevel> inventory =
                inventoryRepo.findByProduct_Id(productId);

        if (inventory.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        List<TransferSuggestion> results = new ArrayList<>();

        for (InventoryLevel source : inventory) {

            List<DemandForecast> forecasts =
                    forecastRepo.findByStoreAndProductAndForecastDateAfter(
                            source.getStore(),
                            source.getProduct(),
                            LocalDate.now());

            if (forecasts.isEmpty()) {
                continue;
            }

            int demand = forecasts.get(0).getPredictedDemand();

            if (source.getQuantity() > demand) {

                for (InventoryLevel target : inventory) {

                    if (target.getQuantity() < demand) {

                        TransferSuggestion ts = new TransferSuggestion();
                        ts.setSourceStore(source.getStore());
                        ts.setTargetStore(target.getStore());
                        ts.setProduct(source.getProduct());
                        ts.setQuantity(
                                source.getQuantity() - demand);
                        ts.setPriority("HIGH");

                        results.add(
                                suggestionRepo.save(ts)
                        );
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
                .orElseThrow(() ->
                        new ResourceNotFoundException("Suggestion not found"));
    }
}
