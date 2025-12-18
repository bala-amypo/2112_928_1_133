package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import jakarta.validation.Valid;
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
    public InventoryLevel save(@Valid @RequestBody InventoryLevel inventoryLevel) {
        return service.save(inventoryLevel);
    }

    @GetMapping
    public List<InventoryLevel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InventoryLevel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
