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
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return repository.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return repository.findByProduct_Id(productId);
    }

    @Override
    public InventoryLevel save(InventoryLevel level) {
        return repository.save(level);
    }
}
