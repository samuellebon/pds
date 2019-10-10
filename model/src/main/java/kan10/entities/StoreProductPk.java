package kan10.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoreProductPk implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "price_id")
    private Long priceId;

    public StoreProductPk() { }

    public StoreProductPk(Long productId, Long storeId, Long priceId) {
        this.productId = productId;
        this.storeId = storeId;
        this.priceId = priceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }
}