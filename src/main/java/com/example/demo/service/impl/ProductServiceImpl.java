package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {

        repository.findBySku(product.getSku()).ifPresent(p -> {
            throw new BadRequestException("SKU already exists");
        });

        // primitive boolean → no null check
        product.setActive(true);

        return repository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
