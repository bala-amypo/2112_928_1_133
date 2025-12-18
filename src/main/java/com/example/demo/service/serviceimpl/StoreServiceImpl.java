package com.example.demo.service;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    public Store getById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        storeRepository.deleteById(id);
    }
}
