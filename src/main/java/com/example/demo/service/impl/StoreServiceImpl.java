package com.example.demo.service.impl;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repo;

    public StoreServiceImpl(StoreRepository repo) {
        this.repo = repo;
    }

    @Override
    public Store save(Store store) {
        return repo.save(store);
    }

    @Override
    public Store getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Store> getAll() {
        return repo.findAll();
    }
}
