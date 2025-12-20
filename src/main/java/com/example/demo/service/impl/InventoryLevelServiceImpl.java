package com.example.demo.service.impl;

import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository transferSuggestionRepository;
    private final InventoryLevelRepository inventoryLevelRepository;
    private final DemandForecastRepository demandForecastRepository;
    private final StoreRepository storeRepository;

    // MUST be in this exact order to pass technical constraint #1
    public InventoryBalancerServiceImpl(
            TransferSuggestionRepository transferSuggestionRepository,
            InventoryLevelRepository inventoryLevelRepository,
            DemandForecastRepository demandForecastRepository,
            StoreRepository storeRepository) {
        this.transferSuggestionRepository = transferSuggestionRepository;
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.demandForecastRepository = demandForecastRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {
        // Validation: Inactive products must throw BadRequestException [cite: 283, 284]
        // Validation: Missing forecasts must throw "No forecast found" [cite: 191, 288]
    }
}