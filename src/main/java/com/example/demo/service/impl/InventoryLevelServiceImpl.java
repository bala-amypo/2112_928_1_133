package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryLevelRepository,
                                     StoreRepository storeRepository,
                                     ProductRepository productRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inv) {

        if (inv.getQuantity() < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        Store store = storeRepository.findById(inv.getStore().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Product product = productRepository.findById(inv.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        InventoryLevel existing =
                inventoryLevelRepository.findByStoreAndProduct(store, product);

        if (existing != null) {
            existing.setQuantity(inv.getQuantity());
            return inventoryLevelRepository.save(existing);
        }

        inv.setStore(store);
        inv.setProduct(product);
        return inventoryLevelRepository.save(inv);
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryLevelRepository.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryLevelRepository.findByProduct_Id(productId);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        InventoryLevel inv =
                inventoryLevelRepository.findByStoreAndProduct(store, product);

        if (inv == null) {
            throw new ResourceNotFoundException("Inventory not found");
        }

        return inv;
    }
}
