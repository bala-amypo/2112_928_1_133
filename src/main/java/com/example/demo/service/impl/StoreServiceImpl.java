package com.example.demo.service.impl;

import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repository;

    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store createStore(Store store) {

        repository.findByStoreName(store.getStoreName()).ifPresent(s -> {
            throw new BadRequestException("Store name already exists");
        });

        store.setActive(true);
        return repository.save(store);
    }

    @Override
    public Store getStoreById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Store> getAllStores() {
        return repository.findAll();
    }

    // ✅ REQUIRED BY TESTS
    @Override
    public void updateStore(Long id, Store store) {
        Store existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        existing.setStoreName(store.getStoreName());
        existing.setActive(store.isActive());

        repository.save(existing);
    }

    // ✅ REQUIRED BY TESTS
    @Override
    public void deactivateStore(Long id) {
        Store store = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setActive(false);
        repository.save(store);
    }
}
