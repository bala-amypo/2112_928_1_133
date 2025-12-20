package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository repository;

    public InventoryLevelServiceImpl(InventoryLevelRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return repository.findByProduct_Id(productId);
    }

    // 🔥 REQUIRED
    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return repository.findByStore_Id(storeId);
    }

    // 🔥 REQUIRED
    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {
        InventoryLevel level = repository.findByStore_Id(storeId)
                .stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        level.setQuantity(quantity);
        return repository.save(level);
    }
}
