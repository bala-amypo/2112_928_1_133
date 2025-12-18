package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // CREATE PRODUCT
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<Product> all() {
        return service.getAll();
    }
}
