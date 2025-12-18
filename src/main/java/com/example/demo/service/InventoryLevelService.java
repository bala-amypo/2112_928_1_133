package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    List<InventoryLevel> getInventoryByProductId(Long productId);

    InventoryLevel save(InventoryLevel inventoryLevel);

    InventoryLevel getById(Long id);

    List<InventoryLevel> getAll();

    void delete(Long id);
}
