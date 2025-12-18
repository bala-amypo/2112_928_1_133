package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository repository;

    public InventoryLevelServiceImpl(InventoryLevelRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryLevel save(InventoryLevel inventoryLevel) {
        return repository.save(inventoryLevel);
    }

    @Override
    public List<InventoryLevel> getInventoryByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public List<InventoryLevel> getAll() {
        return repository.findAll();
    }
}
