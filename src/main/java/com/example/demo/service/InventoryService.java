package com.example.demo.service;

import com.example.demo.entity.Inventory;
import java.util.List;

public interface InventoryService {

    Inventory save(Inventory inventory);

    Inventory getById(Long id);

    List<Inventory> getAll();

    Inventory update(Long id, Inventory inventory);

    void delete(Long id);   // 👈 THIS METHOD MUST EXIST
}
