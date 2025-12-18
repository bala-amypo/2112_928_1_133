package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    InventoryLevel save(InventoryLevel inventoryLevel);

    List<InventoryLevel> getAll();

    InventoryLevel getById(Long id);

    List<InventoryLevel> getInventoryByProductId(Long productId);

    void delete(Long id);
}
