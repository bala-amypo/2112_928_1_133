package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repo;

    public InventoryServiceImpl(InventoryRepository repo) {
        this.repo = repo;
    }

    public InventoryLevel save(InventoryLevel inventory) {
        return repo.save(inventory);
    }

    public List<InventoryLevel> getAll() {
        return repo.findAll();
    }

    public InventoryLevel getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
