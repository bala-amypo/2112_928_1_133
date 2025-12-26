package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryLevelService inventoryLevelService;

    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel inv) {
        return inventoryLevelService.createOrUpdateInventory(inv);
    }

    @PutMapping("/update")
    public InventoryLevel update(@RequestBody InventoryLevel inv) {
        return inventoryLevelService.createOrUpdateInventory(inv);
    }

    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> getByStore(@PathVariable Long storeId) {
        return inventoryLevelService.getInventoryForStore(storeId);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryLevel> getByProduct(@PathVariable Long productId) {
        return inventoryLevelService.getInventoryForProduct(productId);
    }
}
