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
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getStore() != null && i.getStore().getId().equals(storeId))
                .toList();
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return repository.findAll()
                .stream()
                .filter(i -> i.getProduct() != null && i.getProduct().getId().equals(productId))
                .toList();
    }
}
