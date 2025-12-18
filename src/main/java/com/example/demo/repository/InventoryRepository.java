package com.example.demo.repository;

import com.example.demo.entity.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryLevel, Long> {
}
