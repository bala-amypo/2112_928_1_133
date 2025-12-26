package com.example.demo.controller;

import com.example.demo.entity.TransferSuggestion;
import com.example.demo.service.InventoryBalancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
public class TransferSuggestionController {

    private final InventoryBalancerService balancerService;

    // Generate suggestions for a product
    @PostMapping("/generate/{productId}")
    public List<TransferSuggestion> generate(@PathVariable Long productId) {
        return balancerService.generateSuggestions(productId);
    }

    // Get suggestion by ID
    @GetMapping("/{id}")
    public TransferSuggestion getById(@PathVariable Long id) {
        return balancerService.getSuggestionById(id);
    }
}
