package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryLevelService inventoryService;

    @PostMapping
    public InventoryLevel createOrUpdate(@RequestBody InventoryLevel inventory) {
        return inventoryService.createOrUpdateInventory(inventory);
    }
}
