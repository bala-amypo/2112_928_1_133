package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    InventoryLevel createOrUpdateInventory(InventoryLevel inventory);

    InventoryLevel getInventory(Long storeId, Long productId);

    // ✅ REQUIRED BY TESTS
    List<InventoryLevel> getInventoryForStore(Long storeId);

    // ✅ REQUIRED BY TESTS
    List<InventoryLevel> getInventoryForProduct(Long productId);
}
