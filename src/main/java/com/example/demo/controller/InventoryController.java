package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryLevelService service;

    public InventoryController(InventoryLevelService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel inventoryLevel) {
        return service.save(inventoryLevel);
    }

    @GetMapping
    public List<InventoryLevel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{productId}")
    public List<InventoryLevel> getByProduct(@PathVariable Long productId) {
        return service.getInventoryByProductId(productId);
    }
}
