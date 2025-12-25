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

    // ⚠️ DO NOT CHANGE ORDER
    public InventoryBalancerServiceImpl(
            TransferSuggestionRepository suggestionRepo,
            InventoryLevelRepository inventoryRepo,
            DemandForecastRepository forecastRepo,
            StoreRepository storeRepo
    ) {
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

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel inv : inventories) {
            List<DemandForecast> forecasts =
                    forecastRepo.findByStoreAndProductAndForecastDateAfter(
                            inv.getStore(), inv.getProduct(), LocalDate.now());

            if (forecasts.isEmpty()) continue;

            int forecastQty = forecasts.get(0).getPredictedDemand();
            int diff = inv.getQuantity() - forecastQty;

            if (diff > 0) {
                for (InventoryLevel target : inventories) {
                    if (target.getQuantity() < forecastQty) {
                        TransferSuggestion ts = new TransferSuggestion();
                        ts.setSourceStore(inv.getStore());
                        ts.setTargetStore(target.getStore());
                        ts.setProduct(inv.getProduct());
                        ts.setQuantity(Math.min(diff, forecastQty - target.getQuantity()));
                        ts.setPriority("HIGH");
                        suggestions.add(suggestionRepo.save(ts));
                    }
                }
            }
        }

        if (suggestions.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        return suggestions;
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
