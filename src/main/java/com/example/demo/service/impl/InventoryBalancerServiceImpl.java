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
    
    private final TransferSuggestionRepository transferSuggestionRepository;
    private final InventoryLevelRepository inventoryLevelRepository;
    private final DemandForecastRepository demandForecastRepository;
    private final StoreRepository storeRepository;
    
    public InventoryBalancerServiceImpl(TransferSuggestionRepository transferSuggestionRepository,
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
        List<InventoryLevel> inventoryLevels = inventoryLevelRepository.findByProduct_Id(productId);
        
        if (inventoryLevels.isEmpty()) {
            throw new BadRequestException("No inventory found for product");
        }
        
        // Check if product is active
        Product product = inventoryLevels.get(0).getProduct();
        if (!product.getActive()) {
            throw new BadRequestException("Product is inactive");
        }
        
        List<TransferSuggestion> suggestions = new ArrayList<>();
        
        // Simple balancing logic: find stores with high inventory and low demand
        for (InventoryLevel sourceInventory : inventoryLevels) {
            if (sourceInventory.getQuantity() > 10) { // Threshold for overstocked
                List<DemandForecast> forecasts = demandForecastRepository
                        .findByStoreAndProductAndForecastDateAfter(
                                sourceInventory.getStore(), 
                                sourceInventory.getProduct(), 
                                LocalDate.now());
                
                if (forecasts.isEmpty()) {
                    throw new BadRequestException("No forecast found");
                }
                
                // Find understocked stores
                for (InventoryLevel targetInventory : inventoryLevels) {
                    if (!targetInventory.getStore().getId().equals(sourceInventory.getStore().getId()) &&
                        targetInventory.getQuantity() < 5) { // Threshold for understocked
                        
                        TransferSuggestion suggestion = new TransferSuggestion();
                        suggestion.setSourceStore(sourceInventory.getStore());
                        suggestion.setTargetStore(targetInventory.getStore());
                        suggestion.setProduct(product);
                        suggestion.setSuggestedQuantity(5);
                        suggestion.setPriority("HIGH");
                        suggestion.setStatus("PENDING");
                        
                        suggestions.add(transferSuggestionRepository.save(suggestion));
                    }
                }
            }
        }
        
        return suggestions;
    }
    
    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return transferSuggestionRepository.findBySourceStoreId(storeId);
    }
    
    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return transferSuggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}