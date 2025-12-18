// /* package com.example.demo.service.impl;

// import com.example.demo.entity.InventoryLevel;
// import com.example.demo.repository.InventoryLevelRepository;
// import com.example.demo.service.InventoryService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class InventoryServiceImpl implements InventoryService {

//     private final InventoryLevelRepository repository;

//     public InventoryServiceImpl(InventoryLevelRepository repository) {
//         this.repository = repository;
//     }

//     @Override
//     public InventoryLevel create(InventoryLevel inventoryLevel) {
//         return repository.save(inventoryLevel);
//     }

//     @Override
//     public InventoryLevel getById(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     @Override
//     public List<InventoryLevel> getAll() {
//         return repository.findAll();
//     }

//     @Override
//     public InventoryLevel update(Long id, InventoryLevel inventoryLevel) {
//         inventoryLevel.setId(id);
//         return repository.save(inventoryLevel);
//     }

//     @Override
//     public void delete(Long id) {
//         repository.deleteById(id);
//     }
// }
//  */