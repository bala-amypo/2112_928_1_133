package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Store;
import com.example.demo.entity.TransferSuggestion;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.TransferSuggestionRepository;
import com.example.demo.service.InventoryBalancerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

            DemandForecast forecast = forecastRepo
                    .findByStoreAndProduct(inv.getStore(), inv.getProduct())
                    .orElseThrow(() -> new BadRequestException("No forecast found"));

            if (inv.getQuantity() > forecast.getForecastQuantity()) {

                // 🔥 Find a different target store
                Store targetStore = storeRepo.findAll().stream()
                        .filter(s -> !s.getId().equals(inv.getStore().getId()))
                        .findFirst()
                        .orElse(inv.getStore());

                TransferSuggestion ts = new TransferSuggestion();
                ts.setSourceStore(inv.getStore());
                ts.setTargetStore(targetStore);
                ts.setProduct(inv.getProduct());
                ts.setQuantity(inv.getQuantity() - forecast.getForecastQuantity());
                ts.setPriority("HIGH");
                ts.setGeneratedAt(LocalDateTime.now()); // ✅ REQUIRED BY TESTS

                suggestions.add(suggestionRepo.save(ts));
            }
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
