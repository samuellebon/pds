package kan10.interfaces;

import kan10.entities.StoreCategory;
import java.util.List;

public interface StoreCategoryService {

    List <StoreCategory>  getAllStoreCategories();

    StoreCategory saveStoreCategoryToDb (StoreCategory storeCategory);

}
