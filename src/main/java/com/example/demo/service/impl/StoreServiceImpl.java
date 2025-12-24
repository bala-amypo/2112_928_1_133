package com.example.demo.service.impl;

import com.example.demo.entity.Store;
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
        return repository.save(store);
    }

    @Override
    public Store getStoreById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    @Override
    public List<Store> getAllStores() {
        return repository.findAll();
    }

    // ✅ REQUIRED BY TEST
    @Override
    public Store updateStore(Long id, Store updated) {
        Store store = getStoreById(id);

        store.setStoreName(updated.getStoreName());
        store.setAddress(updated.getAddress());
        store.setRegion(updated.getRegion());
        store.setActive(updated.getActive());

        return repository.save(store);
    }

    @Override
    public void deactivateStore(Long storeId) {
        Store store = getStoreById(storeId);
        store.setActive(false);
        repository.save(store);
    }
}
