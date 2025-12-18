package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryService {

    InventoryLevel addInventory(InventoryLevel inventory);

    InventoryLevel getInventoryById(Long id);

    List<InventoryLevel> getAllInventory();

    InventoryLevel updateInventory(Long id, InventoryLevel inventory);

    void deleteInventory(Long id);
}
