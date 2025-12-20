package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.List;

public interface InventoryLevelService {

    // USED BY TESTS + CONTROLLER
    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    // INTERNAL / OTHER USES
    InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel);

    List<InventoryLevel> getInventoryForProduct(Long productId);

    InventoryLevel getInventory(Long storeId, Long productId);
}
