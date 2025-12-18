package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-levels")
public class InventoryController {

    private final InventoryLevelService service;

    public InventoryController(InventoryLevelService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel level) {
        return service.save(level);
    }

    @GetMapping("/{id}")
    public InventoryLevel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<InventoryLevel> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
