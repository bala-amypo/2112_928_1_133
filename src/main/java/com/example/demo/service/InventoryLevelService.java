package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel);

    InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity);

    List<InventoryLevel> getInventoryForStore(Long storeId);

    List<InventoryLevel> getInventoryForProduct(Long productId);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    List<InventoryLevel> getInventoryByProduct(Long productId);
}
