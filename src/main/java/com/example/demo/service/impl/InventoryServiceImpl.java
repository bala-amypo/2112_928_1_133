package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryLevel save(InventoryLevel inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<InventoryLevel> getAll() {
        return repository.findAll();
    }

    @Override
    public InventoryLevel getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
