package kan10.controller;

import kan10.dao.ProductDao;
import kan10.entities.Product;
import kan10.entities.StoreProduct;
import kan10.service.ProductCategoryServiceImpl;
import kan10.service.ProductServiceImpl;
import kan10.service.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller : Manage products
 * @author Suriya
 */
@Controller
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @Autowired
    StoreServiceImpl storeService;

    /**
     * Homepage for products
     * @return products-manage-view
     */
    @GetMapping(value = "/productsmanage")
    public String products(){
        return "products-manage-view";
    }

    /**
     * List products
     * @param model
     * @return products
     */
    @GetMapping(value = "/productslist")
    public String productslist(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("allproducts", productService.getAllProducts());
        model.addAttribute("allcategories", productCategoryService.getAllProductsCategories());
        return "products-list-view";
    }

    /**
     * Create product
     * @param product
     * @param model
     * @return
     */
    @PostMapping(value="/createproduct")
    public String saveProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("allproducts", productService.getAllProducts());
        model.addAttribute("allcategories", productCategoryService.getAllProductsCategories());
        productService.createProduct(product);
        return "redirect:/productslist";
    }

    @GetMapping("/searchproduct")
    public String advancedSearchproduct(Model model) {
        model.addAttribute("categories", productCategoryService.getAllProductsCategories());
        model.addAttribute("stores", storeService.getAllStores());
        return "product-search";
    }


    @GetMapping("/compareprices")
    public String comparepices(Model model) {
        model.addAttribute("categories", productCategoryService.getAllProductsCategories());
        model.addAttribute("stores", storeService.getAllStores());
        return "compare-prices";
    }

}
