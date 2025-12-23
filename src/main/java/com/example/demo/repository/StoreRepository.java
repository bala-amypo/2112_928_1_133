package com.example.demo.repository;

import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreName(String storeName);

    @Query("FROM Store s WHERE s.active = true")
    List<Store> findActiveStores();
}
