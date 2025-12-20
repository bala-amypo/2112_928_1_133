package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryRepo;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    // ================= CONTROLLER METHODS =================

    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {

        InventoryLevel inventory = inventoryRepo
                .findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        inventory.setQuantity(quantity);
        return inventoryRepo.save(inventory);
    }

    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return inventoryRepo.findByStore_Id(storeId);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return inventoryRepo.findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

    // ================= TEST-REQUIRED METHODS =================

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel) {
        return inventoryRepo.save(inventoryLevel);
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryRepo.findByStore_Id(storeId);
    }

    // 🔥 THIS METHOD FIXES YOUR CURRENT ERROR
    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryRepo.findByProduct_Id(productId);
    }
}
