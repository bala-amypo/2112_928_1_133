package com.example.demo.balancer;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.DemandForecastService;
import com.example.demo.service.InventoryLevelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiLocationInventoryBalancer {

    private final InventoryLevelService inventoryLevelService;
    private final DemandForecastService demandForecastService;

    // ✅ Constructor required by test cases
    public MultiLocationInventoryBalancer(
            InventoryLevelService inventoryLevelService,
            DemandForecastService demandForecastService) {
        this.inventoryLevelService = inventoryLevelService;
        this.demandForecastService = demandForecastService;
    }

    /**
     * Balances inventory across multiple locations for a product
     *
     * @param productId product id
     * @return Map of storeId -> quantity
     */
    public Map<Long, Integer> balanceInventory(Long productId) {

        Map<Long, Integer> result = new HashMap<>();

        // Get inventory levels
        List<InventoryLevel> inventoryLevels =
                inventoryLevelService.getInventoryByProduct(productId);

        if (inventoryLevels == null || inventoryLevels.isEmpty()) {
            return result;
        }

        for (InventoryLevel level : inventoryLevels) {
            if (level.getStore() != null) {
                Long storeId = level.getStore().getId();
                Integer quantity = level.getQuantity() != null
                        ? level.getQuantity()
                        : 0;
                result.put(storeId, quantity);
            }
        }

        return result;
    }
}
