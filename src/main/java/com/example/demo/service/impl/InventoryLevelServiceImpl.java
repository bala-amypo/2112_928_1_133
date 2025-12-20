package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository repo;

    public InventoryLevelServiceImpl(InventoryLevelRepository repo) {
        this.repo = repo;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventory) {
        return repo.save(inventory);
    }

    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {
        InventoryLevel inv = repo
                .findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        inv.setQuantity(quantity);
        return repo.save(inv);
    }

    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return repo.findByStore_Id(storeId);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return repo.findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

    // 🔥 TEST REQUIRED
    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return repo.findByStore_Id(storeId);
    }
}
