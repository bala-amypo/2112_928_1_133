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

    private final InventoryLevelRepository inventoryLevelRepository;
    private final DemandForecastRepository demandForecastRepository;
    private final TransferSuggestionRepository transferSuggestionRepository;

    public InventoryBalancerServiceImpl(
            InventoryLevelRepository inventoryLevelRepository,
            DemandForecastRepository demandForecastRepository,
            TransferSuggestionRepository transferSuggestionRepository) {

        this.inventoryLevelRepository = inventoryLevelRepository;
        this.demandForecastRepository = demandForecastRepository;
        this.transferSuggestionRepository = transferSuggestionRepository;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {

        List<InventoryLevel> inventory =
                inventoryLevelRepository.findByProduct_Id(productId);

        if (inventory.isEmpty()) {
            throw new BadRequestException("No inventory found");
        }

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel level : inventory) {

            List<DemandForecast> forecasts =
                    demandForecastRepository.findByStoreAndProductAndForecastDateAfter(
                            level.getStore(),
                            level.getProduct(),
                            LocalDate.now()
                    );

            for (DemandForecast forecast : forecasts) {

                int demand = forecast.getPredictedDemand();

                if (level.getQuantity() < demand) {

                    TransferSuggestion suggestion = new TransferSuggestion();
                    suggestion.setSuggestedQuantity(demand - level.getQuantity());
                    suggestion.setReason("Insufficient inventory");

                    suggestions.add(
                            transferSuggestionRepository.save(suggestion)
                    );
                }
            }
        }

        if (suggestions.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        return suggestions;
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return transferSuggestionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Suggestion not found"));
    }
}
