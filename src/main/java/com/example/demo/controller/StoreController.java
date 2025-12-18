package com.example.demo.controller;

import com.example.demo.entity.Store;
import com.example.demo.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService service;

    public StoreCONTROLLER(StoreService service) {
        this.service = service;
    }

    @PostMapping
    public Store save(@RequestBody Store store) {
        return service.save(store);
    }

    @GetMapping
    public List<Store> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Store get(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
