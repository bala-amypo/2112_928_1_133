package com.example.demo.service.impl;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory update(Long id, Inventory inventory) {
        inventory.setId(id);
        return inventoryRepository.save(inventory);
    }

    @Override
    public void delete(Long id) {
        inventoryRepository.deleteById(id);   // ✅ REQUIRED METHOD
    }
}
