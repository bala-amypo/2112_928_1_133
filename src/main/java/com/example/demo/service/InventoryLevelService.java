package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel createOrUpdateInventory(InventoryLevel inventory);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    // 🔥 REQUIRED BY CONTROLLER + TESTS
    List<InventoryLevel> getInventoryForProduct(Long productId);
}
