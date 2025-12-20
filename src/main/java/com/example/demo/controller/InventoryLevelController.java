package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    private final InventoryLevelService inventoryLevelService;

    public InventoryLevelController(InventoryLevelService inventoryLevelService) {
        this.inventoryLevelService = inventoryLevelService;
    }

    // 🔥 TEST CALLS THIS (Map + Header)
    @PostMapping
    public InventoryLevel create(
            @RequestBody Map<String, Object> body,
            @RequestHeader("Authorization") String token
    ) {
        InventoryLevel inventory = new InventoryLevel();
        inventory.setQuantity((Integer) body.get("quantity"));
        inventory.setStoreId(Long.valueOf(body.get("storeId").toString()));
        inventory.setProductId(Long.valueOf(body.get("productId").toString()));

        return inventoryLevelService.createOrUpdateInventory(inventory);
    }

    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> byStore(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") String token
    ) {
        return inventoryLevelService.getInventoryByStore(storeId);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryLevel> byProduct(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String token
    ) {
        return inventoryLevelService.getInventoryForProduct(productId);
    }
}
