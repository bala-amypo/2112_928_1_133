package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel createOrUpdateInventory(InventoryLevel inventory);

    List<InventoryLevel> getInventoryForProduct(Long productId);

    // ✅ REQUIRED BY CONTROLLER & TESTS
    List<InventoryLevel> getInventoryByStore(Long storeId);

    // ✅ REQUIRED BY CONTROLLER & TESTS
    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);
}
