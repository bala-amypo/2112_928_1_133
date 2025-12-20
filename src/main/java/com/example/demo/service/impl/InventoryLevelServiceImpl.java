package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository repository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public InventoryLevelServiceImpl(
            InventoryLevelRepository repository,
            StoreRepository storeRepository,
            ProductRepository productRepository) {
        this.repository = repository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return repository.findByProduct_Id(productId);
    }

    // ✅ REQUIRED
    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return repository.findByStore_Id(storeId);
    }

    // ✅ REQUIRED
    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        InventoryLevel inventory = repository
                .findByStoreAndProduct(store, product)
                .orElseGet(() -> {
                    InventoryLevel il = new InventoryLevel();
                    il.setStore(store);
                    il.setProduct(product);
                    il.setQuantity(0);
                    return il;
                });

        inventory.setQuantity(quantity);
        return repository.save(inventory);
    }
}
