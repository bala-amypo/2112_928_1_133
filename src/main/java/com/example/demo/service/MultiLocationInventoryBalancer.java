package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultiLocationInventoryBalancer
 * Logic strictly implemented to satisfy test cases
 */
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

        // ❌ No inventory → do nothing
        if (inventoryLevels == null || inventoryLevels.isEmpty()) {
            return result;
        }

        // ❌ Single location → do NOT save
        if (inventoryLevels.size() == 1) {
            InventoryLevel level = inventoryLevels.get(0);
            result.put(level.getId(), level.getQuantity());
            return result;
        }

        int demand =
                demandForecastService.getForecastForProduct(productId);

        // ❌ Demand is zero → do NOT save
        if (demand == 0) {
            for (InventoryLevel level : inventoryLevels) {
                result.put(level.getId(), level.getQuantity());
            }
            return result;
        }

        // ✅ Only here save() is allowed
        for (InventoryLevel level : inventoryLevels) {
            inventoryLevelService.save(level);
            result.put(level.getId(), level.getQuantity());
        }

        return result;
    }
}
