package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryRepo;
    private final StoreRepository storeRepo;
    private final ProductRepository productRepo;

    public InventoryLevelServiceImpl(
            InventoryLevelRepository inventoryRepo,
            StoreRepository storeRepo,
            ProductRepository productRepo) {

        this.inventoryRepo = inventoryRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    // ================= CONTROLLER METHODS =================

    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {

        InventoryLevel inventory = inventoryRepo
                .findByStore_IdAndProduct_Id(storeId, productId)
                .orElse(new InventoryLevel());

        inventory.setStore(
                storeRepo.findById(storeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Store not found"))
        );

        inventory.setProduct(
                productRepo.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found"))
        );

        inventory.setQuantity(quantity);

        return inventoryRepo.save(inventory);
    }

    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return inventoryRepo.findByStore_Id(storeId);
    }

    // ================= TEST REQUIRED METHODS =================

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryRepo.findByProduct_Id(productId);
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel) {
        return inventoryRepo.save(inventoryLevel);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return inventoryRepo.findByStore_IdAndProduct_Id(storeId, productId)
            .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
}

}
