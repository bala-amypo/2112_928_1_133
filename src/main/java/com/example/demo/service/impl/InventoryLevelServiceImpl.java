package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryLevelRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventory) {
        return inventoryLevelRepository.save(inventory);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryLevelRepository.findByProduct_Id(productId);
    }
}
