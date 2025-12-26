package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.TransferSuggestion;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TransferSuggestionRepository;
import com.example.demo.service.InventoryBalancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional  
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final ProductRepository productRepository;
    private final InventoryLevelRepository inventoryRepo;
    private final DemandForecastRepository forecastRepo;
    private final TransferSuggestionRepository suggestionRepo;

   
    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!product.isActive()) {
            throw new BadRequestException("Inactive product");
        }

        List<InventoryLevel> inventories =
                inventoryRepo.findByProduct_Id(productId);

        List<DemandForecast> forecasts =
                forecastRepo.findByProduct_Id(productId);

        List<TransferSuggestion> suggestions = new ArrayList<>();

        for (InventoryLevel inv : inventories) {
            for (DemandForecast fc : forecasts) {

                if (!inv.getStore().getId().equals(fc.getStore().getId())) {

                    int surplus = inv.getQuantity() - fc.getForecastedDemand();

                    if (surplus > 0) {
                        TransferSuggestion ts = new TransferSuggestion();
                        ts.setProduct(product);
                        ts.setSourceStore(inv.getStore());
                        ts.setTargetStore(fc.getStore());
                        ts.setSuggestedQuantity(surplus);
                        ts.setReason("Auto balance based on forecast");

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

   
    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return suggestionRepo.findBySourceStore_Id(storeId);
    }
}
