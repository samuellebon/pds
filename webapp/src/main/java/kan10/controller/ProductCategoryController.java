package kan10.controller;

import kan10.entities.ProductCategory;
import kan10.interfaces.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller : Manage products categories
 * @author Suriya
 */
@Controller
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * List products categories
     * @param model
     * @return products
     */
    @GetMapping(value = "/productscategorieslist")
    public String productscategorieslist(Model model){
        model.addAttribute("productcategory", new ProductCategory());
        model.addAttribute("allproductscategories", productCategoryService.getAllProductsCategories());
        return "products-categories-list-view";
    }

    /**
     * Create product category
     * @param productCategory
     * @param model
     * @return
     */
    @PostMapping(value="/createproductcategory")
    public String saveProduct(@ModelAttribute ProductCategory productCategory, Model model) {
        model.addAttribute("allproductcategories", productCategoryService.getAllProductsCategories());
        productCategoryService.createProductCategory(productCategory);
        return "redirect:/productscategorieslist";
    }
}
