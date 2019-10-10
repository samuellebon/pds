package kan10.mock;

import kan10.common.EnumUtils;
import kan10.common.ProductCategoryTitle;
import kan10.entities.ProductCategory;
import kan10.enums.*;
import kan10.service.ProductCategoryServiceImpl;

class MockProductCategory {

    static void insertProductCategories(ProductCategoryServiceImpl productCategoryService) {

        ProductCategory art = new ProductCategory();
        art.setName(ProductCategoryTitle.ART_CRAFT_SEWING_PARTY_SUPPLIES);
        art.setDescription("");
        art.setKeywords(EnumUtils.getAll(ArtCraftSewingPartySuppliesCategory.class));
        productCategoryService.createProductCategory(art);

        ProductCategory aut = new ProductCategory();
        aut.setName(ProductCategoryTitle.AUTO_TIRES_INDUSTRIAL);
        aut.setDescription("");
        aut.setKeywords(EnumUtils.getAll(AutoTiresIndustrialCategory.class));
        productCategoryService.createProductCategory(aut);

        ProductCategory bab = new ProductCategory();
        bab.setName(ProductCategoryTitle.BABY);
        bab.setDescription("");
        bab.setKeywords(EnumUtils.getAll(BabyCategory.class));
        productCategoryService.createProductCategory(bab);

        ProductCategory clo = new ProductCategory();
        clo.setName(ProductCategoryTitle.CLOTHING_SHOES_ACCESSORIES);
        clo.setDescription("");
        clo.setKeywords(EnumUtils.getAll(ClothingShoesAccessoriesCategory.class));
        productCategoryService.createProductCategory(clo);

        ProductCategory ele = new ProductCategory();
        ele.setName(ProductCategoryTitle.ELECTRONICS_OFFICE);
        ele.setDescription("");
        ele.setKeywords(EnumUtils.getAll(ElectronicOfficeCategory.class));
        productCategoryService.createProductCategory(ele);

        ProductCategory foo = new ProductCategory();
        foo.setName(ProductCategoryTitle.FOOD_HOUSEHOLD_GETS);
        foo.setDescription("");
        foo.setKeywords(EnumUtils.getAll(FoodHouseholdGetsCategory.class));
        productCategoryService.createProductCategory(foo);

        ProductCategory hom = new ProductCategory();
        hom.setName(ProductCategoryTitle.HOME_FURNITURE_APPLIANCES);
        hom.setDescription("");
        hom.setKeywords(EnumUtils.getAll(HomeFurnitureApplicancesCategory.class));
        productCategoryService.createProductCategory(hom);

        ProductCategory mov = new ProductCategory();
        mov.setName(ProductCategoryTitle.MOVIES_VIDEO_BOOKS);
        mov.setDescription("");
        mov.setKeywords(EnumUtils.getAll(MoviesVideoBooksCategory.class));
        productCategoryService.createProductCategory(mov);

        ProductCategory pha = new ProductCategory();
        pha.setName(ProductCategoryTitle.PHARMACY_HEALTH_BEAUTY);
        pha.setDescription("");
        pha.setKeywords(EnumUtils.getAll(PharmacyHealthBeautyCategory.class));
        productCategoryService.createProductCategory(pha);

        ProductCategory spo = new ProductCategory();
        spo.setName(ProductCategoryTitle.SPORT_FITNESS_OUTDOORS);
        spo.setDescription("");
        spo.setKeywords(EnumUtils.getAll(SportFitnessOutdoorsCategory.class));
        productCategoryService.createProductCategory(spo);

        ProductCategory toy = new ProductCategory();
        toy.setName(ProductCategoryTitle.TOYS_VIDEO_GAMES);
        toy.setDescription("");
        toy.setKeywords(EnumUtils.getAll(ToysVideoGamesCategory.class));
        productCategoryService.createProductCategory(toy);

    }

}