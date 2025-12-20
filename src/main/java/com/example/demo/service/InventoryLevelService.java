package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel createOrUpdateInventory(InventoryLevel inventory);

    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    InventoryLevel getInventory(Long storeId, Long productId);

    // 🔥 REQUIRED BY TEST
    List<InventoryLevel> getInventoryForStore(Long storeId);
}
