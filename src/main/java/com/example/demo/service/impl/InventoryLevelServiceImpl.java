package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryRepository;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inv) {
        if (inv.getQuantity() < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        InventoryLevel existing =
                inventoryRepository.findByStoreAndProduct(inv.getStore(), inv.getProduct());

        if (existing != null) {
            existing.setQuantity(inv.getQuantity());
            return inventoryRepository.save(existing);
        }

        return inventoryRepository.save(inv);
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryRepository.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryRepository.findByProduct_Id(productId);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return inventoryRepository.findAll().stream()
                .filter(i -> i.getStore().getId().equals(storeId)
                        && i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }
}
