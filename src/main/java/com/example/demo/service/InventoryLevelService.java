package com.example.demo.service;

import com.example.demo.entity.InventoryLevel;
import java.util.List;

public interface InventoryLevelService {

    List<InventoryLevel> getInventoryForStore(Long storeId);
}
