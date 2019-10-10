package kan10.entities;

import javax.persistence.*;

@Entity
@Table(name="storeDelivery")
public class StoreDelivery  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="price")
    private double price;

    @Column(name="deliveryTime")
    private int deliveryTime;

    @Column(name="description")
    private String description;

    @Column(name="storeId")
    private String storeId;

    @Column(name="productId")
    private String productId;

    public StoreDelivery(){}
    public StoreDelivery(double price, int deliveryTime, String description, String storeId,String productId) {
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.description = description;
        this.storeId = storeId;
        this.productId = productId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
