@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String address;
    private String region;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStoreName() {
        return storeName;
    }

    public Store setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Store setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Store setRegion(String region) {
        this.region = region;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Store setActive(boolean active) {
        this.active = active;
        return this;
    }
}
