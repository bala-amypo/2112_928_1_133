package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    private final InventoryLevelService service;

    public InventoryLevelController(InventoryLevelService service) {
        this.service = service;
    }

    // 🔥 TEST CALLS Map → Object
    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel inventory) {
        return service.createOrUpdateInventory(inventory);
    }

    @PutMapping("/update")
    public InventoryLevel update(
            @RequestParam Long storeId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {

        return service.updateInventory(storeId, productId, quantity);
    }

    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> getByStore(@PathVariable Long storeId) {
        return service.getInventoryForStore(storeId);
    }

    @GetMapping
    public InventoryLevel getOne(
            @RequestParam Long storeId,
            @RequestParam Long productId) {

        return service.getInventory(storeId, productId);
    }
}
