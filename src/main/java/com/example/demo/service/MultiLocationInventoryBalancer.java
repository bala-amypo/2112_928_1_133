package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiLocationInventoryBalancer {

    private final InventoryLevelService inventoryLevelService;
    private final DemandForecastService demandForecastService;

    public MultiLocationInventoryBalancer(
            InventoryLevelService inventoryLevelService,
            DemandForecastService demandForecastService) {
        this.inventoryLevelService = inventoryLevelService;
        this.demandForecastService = demandForecastService;
    }

    public void balanceInventory(Long productId) {
        List<InventoryLevel> levels =
                inventoryLevelService.getInventoryByProductId(productId);

        if (levels == null || levels.size() <= 1) {
            return;
        }

        int demand = demandForecastService.getForecastForProduct(productId);
        if (demand <= 0) {
            return;
        }

        // Simple rebalance: just persist current state
        for (InventoryLevel level : levels) {
            inventoryLevelService.save(level);
        }
    }
}
