package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.List;

public interface InventoryLevelService {

    InventoryLevel save(InventoryLevel level);

    List<InventoryLevel> getInventoryByProductId(Long productId);

    InventoryLevel getById(Long id);

    List<InventoryLevel> getAll();

    void delete(Long id);
}
