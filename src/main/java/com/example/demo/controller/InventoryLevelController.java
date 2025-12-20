package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    private final InventoryLevelService inventoryLevelService;

    public InventoryLevelController(InventoryLevelService inventoryLevelService) {
        this.inventoryLevelService = inventoryLevelService;
    }

    // ✅ UPDATE INVENTORY
    @PutMapping("/update")
    public InventoryLevel updateInventory(
            @RequestParam Long storeId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {

        return inventoryLevelService.updateInventory(storeId, productId, quantity);
    }

    // ✅ GET INVENTORY BY STORE
    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> getInventoryByStore(@PathVariable Long storeId) {
        return inventoryLevelService.getInventoryByStore(storeId);
    }

    // ✅ GET INVENTORY BY STORE + PRODUCT
    @GetMapping("/store/{storeId}/product/{productId}")
    public InventoryLevel getInventory(
            @PathVariable Long storeId,
            @PathVariable Long productId) {

        return inventoryLevelService.getInventory(storeId, productId);
    }
}
