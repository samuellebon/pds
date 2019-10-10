package kan10.entities;

import javax.persistence.*;

@Entity
@Table(name="order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_store_product")
    private StoreProduct storeProduct;

    private int quantity;

    public OrderLine(StoreProduct storeProduct, int quantity) {
        this.storeProduct = storeProduct;
        this.quantity = quantity;
    }

    public OrderLine() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    public void setStoreProduct(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
