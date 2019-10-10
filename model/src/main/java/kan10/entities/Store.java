package kan10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="label")
    private String label;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_store_location")
    public Location location ;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_store_category")
    public StoreCategory storeCategory;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ProductCategory> productCategories;

    public String categoriesName;


    public Store () {    }

    public Store(String label, Location location, StoreCategory storeCategory) {
        this(label, location, storeCategory,new HashSet<>());
    }

    public Store(String label, Location location, StoreCategory storeCategory, Set<ProductCategory> productCategories) {
        this.label = label;
        this.location = location;
        this.storeCategory = storeCategory;
        this.productCategories = productCategories;

        StringBuilder labelBuilder = new StringBuilder();
        productCategories.forEach(c -> labelBuilder.append(c.getName()).append(" & "));

        if (!productCategories.isEmpty()) this.categoriesName = labelBuilder.deleteCharAt(labelBuilder.toString().length()-2).toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public StoreCategory getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(StoreCategory storeCategory) {
        this.storeCategory = storeCategory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
}
