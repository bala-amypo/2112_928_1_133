package com.example.demo;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.DemandForecastService;
import com.example.demo.service.InventoryLevelService;
import com.example.demo.service.MultiLocationInventoryBalancer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultiLocationInventoryBalancerTest {

    @Mock
    private InventoryLevelService inventoryLevelService;

    @Mock
    private DemandForecastService demandForecastService;

    @InjectMocks
    private MultiLocationInventoryBalancer balancer;

    private InventoryLevel store1;
    private InventoryLevel store2;

    @BeforeEach
    void setup() {
        store1 = new InventoryLevel();
        store1.setId(1L);
        store1.setProductId(100L);
        store1.setQuantity(50);

        store2 = new InventoryLevel();
        store2.setId(2L);
        store2.setProductId(100L);
        store2.setQuantity(10);
    }

    @Test
    void testBalanceInventory_WhenDemandIsHigherAtOneLocation() {
        when(inventoryLevelService.getInventoryByProductId(100L))
                .thenReturn(Arrays.asList(store1, store2));

        when(demandForecastService.getForecastForProduct(100L))
                .thenReturn(80);

        balancer.balanceInventory(100L);

        verify(inventoryLevelService, atLeastOnce()).save(any(InventoryLevel.class));
    }

    @Test
    void testBalanceInventory_WhenNoInventoryExists() {
        when(inventoryLevelService.getInventoryByProductId(200L))
                .thenReturn(List.of());

        balancer.balanceInventory(200L);

        verify(inventoryLevelService, never()).save(any());
    }

    @Test
    void testBalanceInventory_WhenDemandIsZero() {
        when(inventoryLevelService.getInventoryByProductId(100L))
                .thenReturn(Arrays.asList(store1, store2));

        when(demandForecastService.getForecastForProduct(100L))
                .thenReturn(0);

        balancer.balanceInventory(100L);

        verify(inventoryLevelService, never()).save(any());
    }

    @Test
    void testBalanceInventory_WithSingleLocation() {
        when(inventoryLevelService.getInventoryByProductId(100L))
                .thenReturn(List.of(store1));

        when(demandForecastService.getForecastForProduct(100L))
                .thenReturn(20);

        balancer.balanceInventory(100L);

        verify(inventoryLevelService, never()).save(any());
    }

    @Test
    void testBalanceInventory_DoesNotThrowException() {
        when(inventoryLevelService.getInventoryByProductId(anyLong()))
                .thenReturn(Arrays.asList(store1, store2));

        when(demandForecastService.getForecastForProduct(anyLong()))
                .thenReturn(50);

        assertDoesNotThrow(() -> balancer.balanceInventory(100L));
    }
}
