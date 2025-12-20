package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    void updateInventory(Long storeId, Long productId, Integer quantity);

    InventoryLevel getInventory(Long storeId, Long productId);

    List<InventoryLevel> getInventoryByStore(Long storeId);

    // required alias for tests
    List<InventoryLevel> getInventoryForStore(Long storeId);
}
