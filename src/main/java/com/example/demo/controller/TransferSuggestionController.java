package com.example.demo.controller;

import com.example.demo.entity.TransferSuggestion;
import com.example.demo.service.InventoryBalancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
public class TransferSuggestionController {

    @Autowired
    private InventoryBalancerService inventoryBalancerService;

    @PostMapping("/generate/{productId}")
    public List<TransferSuggestion> generate(@PathVariable Long productId) {
        return inventoryBalancerService.generateSuggestions(productId);
    }

    @GetMapping("/{id}")
    public TransferSuggestion getById(@PathVariable Long id) {
        return inventoryBalancerService.getSuggestionById(id);
    }
}
