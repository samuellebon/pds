package kan10.entities;

import javax.persistence.*;

/**
 * Entity class to manage Product object from database
 * @author Suriya
 */
@Entity
@Table(name="product")
public class Product {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_product_category")
    private ProductCategory productCategory;

    private String name;

    private String description;

    private int weight;

    private int width;

    private int length;

    private int height;

    private String keywords;

    // Constructor
    public Product () {    }

    public Product(String name, String description, int weight, int width, int length, int height, ProductCategory productCategory, String keywords) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.productCategory = productCategory;
        this.keywords = keywords;
    }

    public Product(String name, String description, int weight, int width, int length, int height, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.productCategory = productCategory;
    }

    public Product(String name,ProductCategory productCategory, String keywords) {
        this.name = name;
        this.productCategory = productCategory;
        this.keywords = keywords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
