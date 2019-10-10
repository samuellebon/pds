package kan10.api;

import com.querydsl.core.BooleanBuilder;
import kan10.entities.Product;
import kan10.entities.StoreProduct;
import kan10.service.ProductServiceImpl;
import kan10.service.StoreProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductApi {

    private final
    ProductServiceImpl productService;

    private final StoreProductServiceImpl storeProductService;

    @Autowired
    public ProductApi(ProductServiceImpl productService, StoreProductServiceImpl storeProductService) {
        this.productService = productService;
        this.storeProductService = storeProductService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public DataTablesOutput<Product> searchProduct(@Valid @RequestBody DataTablesInput input, @RequestParam Map<String, String> requestParameters) {

        Optional<String> name = Optional.ofNullable(requestParameters.get("name"));
        Optional<String> category = Optional.ofNullable(requestParameters.get("category"));

        BooleanBuilder bl = new BooleanBuilder();

        name.ifPresent(s -> bl.and(productService.hasName(s)));
        category.ifPresent(cat -> bl.and(productService.hasCategory(cat)));

        return productService.findAll(input, bl);
    }

    @PostMapping("/storeProduct")
    public DataTablesOutput<StoreProduct> searchStoreProduct(@Valid @RequestBody DataTablesInput input, @RequestParam Map<String, String> requestParameters) {

        Optional<String> name = Optional.ofNullable(requestParameters.get("name"));
        Optional<String> category = Optional.ofNullable(requestParameters.get("category"));
        Optional<String> store = Optional.ofNullable(requestParameters.get("store"));

        BooleanBuilder bl = new BooleanBuilder();

        name.ifPresent(s -> bl.and(storeProductService.hasName(s)));
        category.filter(c -> !c.isEmpty()).ifPresent(c -> bl.and(storeProductService.hasCategory(c)));
        store.filter(s -> !s.isEmpty()).ifPresent(s -> bl.and(storeProductService.hasStore(s)));

        return storeProductService.findAll(input, bl);
    }
}