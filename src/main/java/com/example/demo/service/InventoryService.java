package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryService {

    InventoryLevel save(InventoryLevel inventory);

    List<InventoryLevel> getAll();

    InventoryLevel getById(Long id);

    void delete(Long id);
}
