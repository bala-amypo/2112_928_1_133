@Override
public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {

    InventoryLevel level = repository.findByStore_IdAndProduct_Id(storeId, productId)
            .orElseThrow(() -> new RuntimeException("Inventory not found"));

    level.setQuantity(quantity);
    return repository.save(level);
}

@Override
public List<InventoryLevel> getInventoryByStore(Long storeId) {
    return repository.findByStore_Id(storeId);
}

@Override
public List<InventoryLevel> getInventoryByProduct(Long productId) {
    return repository.findByProduct_Id(productId);
}
