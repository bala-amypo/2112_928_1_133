package com.example.demo.serviceimpl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
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
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
