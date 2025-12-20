package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    private final InventoryLevelService inventoryLevelService;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public InventoryLevelController(
            InventoryLevelService inventoryLevelService,
            StoreRepository storeRepository,
            ProductRepository productRepository) {
        this.inventoryLevelService = inventoryLevelService;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public InventoryLevel create(
            @RequestParam Long storeId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        InventoryLevel inventory = new InventoryLevel()
                .setStore(store)
                .setProduct(product)
                .setQuantity(quantity);

        return inventoryLevelService.createOrUpdateInventory(inventory);
    }

    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> byStore(@PathVariable Long storeId) {
        return inventoryLevelService.getInventoryByStore(storeId);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryLevel> byProduct(@PathVariable Long productId) {
        return inventoryLevelService.getInventoryForProduct(productId);
    }
}
