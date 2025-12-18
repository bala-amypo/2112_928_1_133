@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryLevel create(@RequestBody InventoryLevel inventory) {
        return service.save(inventory);
    }

    @GetMapping
    public List<InventoryLevel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InventoryLevel get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public InventoryLevel update(@PathVariable Long id, @RequestBody InventoryLevel inventory) {
        inventory.setId(id);
        return service.save(inventory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
