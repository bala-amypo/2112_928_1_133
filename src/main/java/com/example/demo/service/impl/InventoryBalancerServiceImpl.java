package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final ProductRepository productRepo;
    private final InventoryLevelRepository inventoryRepo;
    private final DemandForecastRepository forecastRepo;
    private final TransferSuggestionRepository suggestionRepo;

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!product.isActive()) {
            throw new BadRequestException("Product inactive");
        }

        List<InventoryLevel> inventories = inventoryRepo.findByProduct_Id(productId);
        List<DemandForecast> forecasts = forecastRepo.findByProduct_Id(productId);

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel inv : inventories) {
            for (DemandForecast fc : forecasts) {
                if (!inv.getStore().getId().equals(fc.getStore().getId())) {
                    if (inv.getQuantity() > fc.getForecastedDemand()) {
                        TransferSuggestion ts = TransferSuggestion.builder()
                                .product(product)
                                .sourceStore(inv.getStore())
                                .targetStore(fc.getStore())
                                .suggestedQuantity(inv.getQuantity() - fc.getForecastedDemand())
                                .reason("Auto-balanced")
                                .build();
                        suggestions.add(suggestionRepo.save(ts));
                    }
                }
            }
        }
        return suggestions;
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return suggestionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
