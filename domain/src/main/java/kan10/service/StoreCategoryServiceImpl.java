package kan10.service;

import kan10.dao.StoreCategoryDAO;
import kan10.entities.StoreCategory;
import kan10.interfaces.StoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreCategoryServiceImpl implements StoreCategoryService {

    @Autowired
    private StoreCategoryDAO storeCategoryDAO;

    @Override
    public List<StoreCategory> getAllStoreCategories() {
        return storeCategoryDAO.findAll();
    }

    @Override
    public StoreCategory saveStoreCategoryToDb(StoreCategory storeCategory) {
        return storeCategoryDAO.save(storeCategory);
    }

}
