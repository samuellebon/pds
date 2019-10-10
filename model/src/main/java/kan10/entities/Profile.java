package kan10.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductCategory> productCategories;

    public Profile(List<ProductCategory> productCategories ) {

        StringBuilder labelBuilder = new StringBuilder();
        StringBuilder descriptionBuilder = new StringBuilder();
        productCategories.forEach(c -> {
            labelBuilder.append(c.getName(), 0, 3).append("_");
            descriptionBuilder.append(c.getName()).append(" & ");
        });

        this.label = labelBuilder.deleteCharAt(labelBuilder.toString().length()-1).toString();
        this.description = descriptionBuilder.toString().substring(0,descriptionBuilder.toString().length()-3);
        this.productCategories = productCategories;
    }

    public Profile() { }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

}