package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository suggestionRepository;
    private final InventoryLevelRepository inventoryRepository;
    private final DemandForecastRepository forecastRepository;
    private final StoreRepository storeRepository;

    public InventoryBalancerServiceImpl(
            TransferSuggestionRepository suggestionRepository,
            InventoryLevelRepository inventoryRepository,
            DemandForecastRepository forecastRepository,
            StoreRepository storeRepository) {

        this.suggestionRepository = suggestionRepository;
        this.inventoryRepository = inventoryRepository;
        this.forecastRepository = forecastRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {

        List<InventoryLevel> inventoryLevels =
                inventoryRepository.findByProduct_Id(productId);

        if (inventoryLevels.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel inv : inventoryLevels) {
            Store store = inv.getStore();

            if (forecastRepository
                    .findByStoreAndProductAndForecastDateAfter(
                            store,
                            inv.getProduct(),
                            LocalDate.now())
                    .isEmpty()) {
                continue;
            }

            if (inv.getQuantity() > 100) {
                TransferSuggestion ts = new TransferSuggestion();
                ts.setSourceStore(store);
                ts.setTargetStore(store);
                ts.setProduct(inv.getProduct());
                ts.setQuantity(10);
                ts.setPriority("HIGH");
                suggestions.add(suggestionRepository.save(ts));
            }
        }

        if (suggestions.isEmpty()) {
            throw new BadRequestException("No forecast found");
        }

        return suggestions;
    }

    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return suggestionRepository.findBySourceStoreId(storeId);
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return suggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion not found"));
    }
}
