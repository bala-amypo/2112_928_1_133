package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;

import org.springframework.stereotype.Service;

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

        List<InventoryLevel> inventories = inventoryRepo.findAll();
        List<TransferSuggestion> suggestions = new ArrayList<>();

        if (inventories.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        for (InventoryLevel inv : inventories) {

            if (!inv.getProduct().getId().equals(productId)) {
                continue;
            }

            forecastRepo.findByStoreAndProduct(inv.getStore(), inv.getProduct())
                    .ifPresentOrElse(forecast -> {

                        if (inv.getQuantity() > forecast.getForecastQuantity()) {

                            TransferSuggestion ts = new TransferSuggestion();
                            ts.setSourceStore(inv.getStore());
                            ts.setTargetStore(inv.getStore()); // simple logic
                            ts.setProduct(inv.getProduct());
                            ts.setQuantity(inv.getQuantity() - forecast.getForecastQuantity());
                            ts.setPriority("HIGH");

                            suggestions.add(suggestionRepo.save(ts));
                        }

                    }, () -> {
                        throw new BadRequestException("No forecast found");
                    });
        }

        return suggestions;
    }

    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return suggestionRepo.findBySourceStoreIdOrTargetStoreId(storeId, storeId);
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return suggestionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
