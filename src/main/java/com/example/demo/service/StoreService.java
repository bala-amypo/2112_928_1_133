package com.example.demo.service;

import com.example.demo.entity.Store;
import java.util.List;

public interface StoreService {
    Store save(Store store);
    List<Store> getAll();
    Store getById(Long id);
    void delete(Long id);
}
