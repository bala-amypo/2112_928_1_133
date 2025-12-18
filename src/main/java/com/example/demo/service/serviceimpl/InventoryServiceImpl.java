package com.example.demo.serviceimpl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
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

    public InventoryLevel save(InventoryLevel inventory) {
        return repository.save(inventory);
    }

    public List<InventoryLevel> getAll() {
        return repository.findAll9);
    }

    public InventoryLevel getById(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Inventory not found with id " + id));
        }

        public void delete(Long id) {
            repository.deleteById(id);
        }
}
