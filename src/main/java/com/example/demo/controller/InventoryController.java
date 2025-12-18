package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
        }

        @PostMapping
        public InventoryLevel save(@RequestBody InventoryLevel inventory) {
            return service.save(inventory);
        }

        @GetMapping
        public List<InventoryLevel> getAll() {
            return service.getAll();
        }

        @GetMapping("/{id}")
        public InventoryLevel get(@PathVariable Long id) {
            return service.getById(id);
            }

            @DeleteMapping("/{id}")
            public void delete(@PathVariable Long id) {
                service.delete(id);
            }
}
