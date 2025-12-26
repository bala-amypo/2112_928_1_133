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

    private final ProductRepository productRepository;
    private final InventoryLevelRepository inventoryLevelRepository;
    private final DemandForecastRepository demandForecastRepository;
    private final TransferSuggestionRepository transferSuggestionRepository;

    public InventoryBalancerServiceImpl(
            ProductRepository productRepository,
            InventoryLevelRepository inventoryLevelRepository,
            DemandForecastRepository demandForecastRepository,
            TransferSuggestionRepository transferSuggestionRepository
    ) {
        this.productRepository = productRepository;
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.demandForecastRepository = demandForecastRepository;
        this.transferSuggestionRepository = transferSuggestionRepository;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!product.isActive()) {
            throw new BadRequestException("Inactive product");
        }

        List<InventoryLevel> inventories =
                inventoryLevelRepository.findByProduct_Id(productId);

        List<DemandForecast> forecasts =
                demandForecastRepository.findByProduct_Id(productId);

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel inv : inventories) {
            for (DemandForecast forecast : forecasts) {

                if (!inv.getStore().getId().equals(forecast.getStore().getId())) {

                    int excess = inv.getQuantity() - forecast.getForecastedDemand();

                    if (excess > 0) {
                        TransferSuggestion ts = new TransferSuggestion();
                        ts.setProduct(product);
                        ts.setSourceStore(inv.getStore());
                        ts.setTargetStore(forecast.getStore());
                        ts.setSuggestedQuantity(excess);
                        ts.setReason("Auto-balancing based on forecast");

                        suggestions.add(transferSuggestionRepository.save(ts));
                    }
                }
            }
        }

        return suggestions;
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return transferSuggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
