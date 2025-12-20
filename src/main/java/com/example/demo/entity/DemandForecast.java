import java.time.LocalDate;

@Entity
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private int forecastedDemand;

    private LocalDate forecastDate;

    // =========================
    // 🔥 TEST + SERVICE REQUIRED
    // =========================

    public void setStore(Store store) {
        this.store = store;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setForecastedDemand(int demand) {
        this.forecastedDemand = demand;
    }

    // alias used by SERVICE + TEST
    public int getForecastQuantity() {
        return forecastedDemand;
    }

    public void setForecastQuantity(int qty) {
        this.forecastedDemand = qty;
    }

    // test requires this
    public void setForecastDate(LocalDate date) {
        this.forecastDate = date;
    }

    public Long getId() {
        return id;
    }
}
