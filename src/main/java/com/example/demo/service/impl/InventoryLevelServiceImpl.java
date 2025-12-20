package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
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
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        List<InventoryLevel> list = repository.findByStore_Id(storeId);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Inventory not found for store");
        }
        return list;
    }

    // 🔥 TEST EXPECTS THIS METHOD NAME
    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return getInventoryByStore(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        List<InventoryLevel> list = repository.findByProduct_Id(productId);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Inventory not found for product");
        }
        return list;
    }
}
