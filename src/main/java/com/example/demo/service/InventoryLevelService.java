package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel save(InventoryLevel inventoryLevel);
List<InventoryLevel> getInventoryByProductId(Long productId);

    List<InventoryLevel> getAll();

    InventoryLevel getById(Long id);

    void delete(Long id);
}
