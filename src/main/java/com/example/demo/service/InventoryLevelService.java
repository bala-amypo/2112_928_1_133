package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.List;

public interface InventoryLevelService {

    InventoryLevel createInventory(InventoryLevel inventory);

    InventoryLevel getInventory(Long storeId, Long productId);

    List<InventoryLevel> getInventoryForStore(Long storeId);

    InventoryLevel updateInventory(Long storeId, Long productId, int quantity);
}
