package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryLevelService inventoryLevelService;

    public InventoryController(InventoryLevelService inventoryLevelService) {
        this.inventoryLevelService = inventoryLevelService;
    }

    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel inventoryLevel) {
        return inventoryLevelService.createOrUpdateInventory(inventoryLevel);
    }

    @PutMapping("/update")
    public InventoryLevel update(@RequestBody InventoryLevel inventoryLevel) {
        return inventoryLevelService.createOrUpdateInventory(inventoryLevel);
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
