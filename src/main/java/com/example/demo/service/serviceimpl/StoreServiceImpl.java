package com.example.demo.serviceimpl;

import com.example.demo.entity.Store;
import com.example.demo.exception.ResourceNotFoundException;
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

        public Store save(Store store) {
            return repository.save(store);
            }

            public List<Store> getAll() {
                return repository.findAll();
            }

            public Store getById(Long id) {
                return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id " + id));
            }

            public void delete(Long id) {
                repository.deleteById(id);
                }
}
