package com.example.demo.serviceimpl;

import com.example.demo.entity.Store;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository repo;

    public StoreServiceImpl(StoreRepository repo) {
        this.repo = repo;
    }

    public Store save(Store store) {
        return repo.save(store);
    }

    public List<Store> getAll() {
        return repo.findAll();
    }

    public Store getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
