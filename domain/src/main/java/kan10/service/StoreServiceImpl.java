package kan10.service;


import com.querydsl.core.types.Predicate;
import kan10.ObjectUtil;
import kan10.StringUtil;
import kan10.dao.StoreDAO;
import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.Store;

import kan10.interfaces.StoreService;
import kan10.predicate.StorePredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDAO storeDAO;

    @Override
    public Store saveStoreToDb(Store store) {
        return storeDAO.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeDAO.findAll();
    }

    @Override
    public BooleanExpression hasStoreCategory(String storeCat) {
        return StorePredicate.hasStoreCategory(storeCat);
    }

    @Override
    public BooleanExpression hasFloorLevelNumber(int floorLevel) {
        return StorePredicate.hasFloorLevelNumber(floorLevel);
    }

    @Override
    public BooleanExpression hasAisleLabel(String aisle) {
        return StorePredicate.hasAisleLabel(aisle);
    }

    @Override
    public BooleanExpression areaGreaterOrEqual(int areaMin) {
        return StorePredicate.areaGreaterOrEqual(areaMin);
    }

    @Override
    public BooleanExpression areaLesserOrEqual(int areaMax) {
        return StorePredicate.areaLesserOrEqual(areaMax);
    }

    @Override
    public BooleanExpression rentGreaterOrEqual(int rentMin) {
        return StorePredicate.rentGreaterOrEqual(rentMin);
    }

    @Override
    public BooleanExpression rentLesserOrEqual(int rentMax) {
        return StorePredicate.rentLesserOrEqual(rentMax);
    }

    @Override
    public DataTablesOutput<Store> findAll(DataTablesInput input, Predicate predicate) {
        return storeDAO.findAll(input, predicate);
    }

    @Override
    public DataTablesOutput<Store> findAll(DataTablesInput input) {
        return storeDAO.findAll(input);
    }

    public Boolean createValidator(Store store) {

        if(ObjectUtil.isNull(store.getLabel())) return false;
        if(store.getLabel().isEmpty()) return false;
        if(StringUtil.containsOnlyMetacharacter(store.getLabel())) return false;
        if(store.getLabel().length() > 32 ) return false;

        if(ObjectUtil.isNull(store.getStoreCategory())) return false;
        if(ObjectUtil.isNull(store.getLocation())) return false;

        return true;
    }

    public Boolean locationAlreadyAllocated(Store store) {
        ArrayList<Store> allStores = (ArrayList<Store>) storeDAO.findAll();
        return allStores.stream().anyMatch(s -> s.getLocation().equals(store.getLocation()));
    }

    public Boolean createStore(Store store) {
        try {
            if(locationAlreadyAllocated(store)) return false;
            if(!createValidator(store)) return false;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
    
}