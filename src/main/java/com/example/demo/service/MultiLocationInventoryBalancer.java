package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiLocationInventoryBalancer {

    private final InventoryLevelService inventoryLevelService;
    private final DemandForecastService demandForecastService;

    public MultiLocationInventoryBalancer(
            InventoryLevelService inventoryLevelService,
            DemandForecastService demandForecastService) {
        this.inventoryLevelService = inventoryLevelService;
        this.demandForecastService = demandForecastService;
    }

    public Map<Long, Integer> balanceInventory(Long productId) {

        Map<Long, Integer> result = new HashMap<>();

        List<InventoryLevel> inventoryLevels =
                inventoryLevelService.getInventoryByProductId(productId);

        int forecast =
                demandForecastService.getForecastForProduct(productId);

        if (inventoryLevels == null || inventoryLevels.isEmpty()) {
            return result;
        }

        for (InventoryLevel level : inventoryLevels) {
            inventoryLevelService.save(level);
            result.put(level.getId(), level.getQuantity());
        }

        return result;
    }
}
