package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    InventoryLevel createOrUpdateInventory(InventoryLevel inventory);

    InventoryLevel getInventory(Long storeId, Long productId);

    // expected by tests
    List<InventoryLevel> getInventoryForStore(Long storeId);
    List<InventoryLevel> getInventoryForProduct(Long productId);

    // expected by controller
    List<InventoryLevel> getInventoryByStore(Long storeId);
    List<InventoryLevel> getInventoryByProduct(Long productId);
}
