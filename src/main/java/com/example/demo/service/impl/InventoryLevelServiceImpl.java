package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryLevelRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
    }

    @Override
    public InventoryLevel createInventory(InventoryLevel inventory) {
        return inventoryLevelRepository.save(inventory);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return inventoryLevelRepository
                .findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found for storeId=" + storeId +
                                " and productId=" + productId
                        ));
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryLevelRepository.findByStore_Id(storeId);
    }

    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, int quantity) {

        InventoryLevel inventory = inventoryLevelRepository
                .findByStore_IdAndProduct_Id(storeId, productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found for storeId=" + storeId +
                                " and productId=" + productId
                        ));

        inventory.setQuantity(quantity);
        return inventoryLevelRepository.save(inventory);
    }
}
