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

    @Override
    public InventoryLevel addInventory(InventoryLevel inventory) {
        return repo.save(inventory);
    }

    @Override
    public InventoryLevel getInventoryById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

    @Override
    public List<InventoryLevel> getAllInventory() {
        return repo.findAll();
    }

    @Override
    public InventoryLevel updateInventory(Long id, InventoryLevel inventory) {
        InventoryLevel existing = getInventoryById(id);
        existing.setQuantity(inventory.getQuantity());
        return repo.save(existing);
    }

    @Override
    public void deleteInventory(Long id) {
        repo.deleteById(id);
    }
}
