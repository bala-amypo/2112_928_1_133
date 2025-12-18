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
    public InventoryLevel save(InventoryLevel level) {
        return repository.save(level);
    }

    @Override
    public List<InventoryLevel> getInventoryByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public InventoryLevel getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<InventoryLevel> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
