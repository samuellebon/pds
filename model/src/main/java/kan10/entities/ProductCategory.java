package kan10.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class to manage ProductType object from database
 * @author Suriya
 */
@Entity
@Table(name="product_category")
public class ProductCategory {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> keywords;

    public ProductCategory() {
    }

    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
