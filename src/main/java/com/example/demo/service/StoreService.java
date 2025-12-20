package com.example.demo.service;

import com.example.demo.entity.Store;
import java.util.List;

public interface StoreService {

    Store createStore(Store store);

    Store getStoreById(Long id);

    List<Store> getAllStores();

    // ✅ REQUIRED BY TESTS
    void updateStore(Long id, Store store);

    // ✅ REQUIRED BY TESTS
    void deactivateStore(Long id);
}
