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

        List<InventoryLevel> inventory =
                inventoryLevelRepository.findByProduct_Id(productId);

        if (inventory.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel source : inventory) {

            List<DemandForecast> forecasts =
                    demandForecastRepository.findByStoreAndProductAndForecastDateAfter(
                            source.getStore(),
                            source.getProduct(),
                            LocalDate.now()
                    );

            if (forecasts.isEmpty()) {
                continue;
            }

            for (InventoryLevel target : inventory) {

                if (!source.getStore().getId().equals(target.getStore().getId())
                        && source.getQuantity() > target.getQuantity()) {

                    TransferSuggestion suggestion = new TransferSuggestion();
                    suggestion.setSourceStore(source.getStore());
                    suggestion.setTargetStore(target.getStore());
                    suggestion.setProduct(source.getProduct());
                    suggestion.setQuantity(
                            (source.getQuantity() - target.getQuantity()) / 2
                    );
                    suggestion.setPriority("MEDIUM");

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
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return transferSuggestionRepository.findBySourceStoreId(storeId);
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return transferSuggestionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Suggestion not found"));
    }
}
