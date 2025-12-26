package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {
    
    private final InventoryLevelService inventoryLevelService;
    
    public InventoryLevelController(InventoryLevelService inventoryLevelService) {
        this.inventoryLevelService = inventoryLevelService;
    }
    
    @PostMapping
    public ResponseEntity<InventoryLevel> createInventory(@RequestBody InventoryLevel inventory) {
        InventoryLevel created = inventoryLevelService.createOrUpdateInventory(inventory);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/update")
    public ResponseEntity<InventoryLevel> updateInventory(@RequestBody InventoryLevel inventory) {
        InventoryLevel updated = inventoryLevelService.createOrUpdateInventory(inventory);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<InventoryLevel>> getInventoryForStore(@PathVariable Long storeId) {
        List<InventoryLevel> inventory = inventoryLevelService.getInventoryForStore(storeId);
        return ResponseEntity.ok(inventory);
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryLevel>> getInventoryForProduct(@PathVariable Long productId) {
        List<InventoryLevel> inventory = inventoryLevelService.getInventoryForProduct(productId);
        return ResponseEntity.ok(inventory);
    }
}