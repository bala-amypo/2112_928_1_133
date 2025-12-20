package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public InventoryLevelServiceImpl(
            InventoryLevelRepository inventoryLevelRepository,
            StoreRepository storeRepository,
            ProductRepository productRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void updateInventory(Long storeId, Long productId, Integer quantity) {

        if (quantity < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("not found"));

        InventoryLevel inventoryLevel = inventoryLevelRepository
                .findByStoreAndProduct(store, product)
                .orElse(new InventoryLevel());

        inventoryLevel.setStore(store);
        inventoryLevel.setProduct(product);
        inventoryLevel.setQuantity(quantity);
        inventoryLevel.setLastUpdated(LocalDateTime.now());

        inventoryLevelRepository.save(inventoryLevel);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("not found"));

        return inventoryLevelRepository.findByStoreAndProduct(store, product)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return inventoryLevelRepository.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryLevelRepository.findByStore_Id(storeId);
    }
}
