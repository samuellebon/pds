package kan10.entities;

import javax.persistence.*;

@Entity
@Table(name="homeDelivery")
public class HomeDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="price")
    private double price;

    @Column(name="deliveryTime")
    private int deliveryTime;

    @Column(name="description")
    private String description;

    @Column(name="productId")
    private String productId;

    public HomeDelivery(){}
    public HomeDelivery(double price, int deliveryTime, String description, String productId) {
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.description = description;
        this.productId =productId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean equals(HomeDelivery homeDelivery){
        if(homeDelivery != null && this != null){
            if (homeDelivery.getPrice() == this.getPrice() && homeDelivery.getDeliveryTime() == this.getDeliveryTime()
                    && homeDelivery.getDescription().equals(this.getDescription())
                    && homeDelivery.getProductId().equals(this.getProductId())) {
                return true;
            }
        }
        return false;
    }
}
