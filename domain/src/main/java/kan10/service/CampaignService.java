package kan10.service;

import kan10.dao.CampaignDao;
import kan10.entities.*;
import kan10.interfaces.ICampaignService;
import kan10.interfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampaignService implements ICampaignService {

    private final CampaignDao campaignDao;

    private final ClientService clientService;

    private final ProductService productService;

    private StoreServiceImpl storeService;

    private EventService eventService;

    private ProductCategoryServiceImpl productCategoryService;

    private ProfileService profileService;

    private RecommenderSystemService recommenderSystemService;

    @Inject
    public CampaignService(CampaignDao campaignDao, ClientService clientService, ProductService productService, StoreServiceImpl storeService, EventService eventService, ProductCategoryServiceImpl productCategoryService, ProfileService profileService, RecommenderSystemService recommenderSystemService) {
        this.campaignDao = campaignDao;
        this.clientService = clientService;
        this.productService = productService;
        this.storeService = storeService;
        this.eventService = eventService;
        this.productCategoryService = productCategoryService;
        this.profileService = profileService;
        this.recommenderSystemService = recommenderSystemService;
    }

    public CampaignService(CampaignDao campaignDao, ClientService clientService, ProductService productService, StoreServiceImpl storeService, EventService eventService, ProductCategoryServiceImpl productCategoryService, ProfileService profileService) {
        this.campaignDao = campaignDao;
        this.clientService = clientService;
        this.productService = productService;
        this.storeService = storeService;
        this.eventService = eventService;
        this.productCategoryService = productCategoryService;
        this.profileService = profileService;
    }

    CampaignService(CampaignDao campaignDao, ClientService clientService, ProductService productService, EventService eventService) {
        this.campaignDao = campaignDao;
        this.clientService = clientService;
        this.productService = productService;
        this.eventService = eventService;
    }

    @Override
    public boolean createCampaign(Campaign campaign) {
        try {

            if (campaign == null) return false;
            else if (campaign.getEvent() == null) return false;

            eventService.createEvent(campaign.getEvent());
            campaignDao.save(campaign);
            campaingAlgorithm(campaign);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void campaingAlgorithm (Campaign campaign) {

        Logger logger = LoggerFactory.getLogger(CampaignService.class);

        List<Store> finalStores = campaign.getStores();
        List<Product> finalProducts = campaign.getProducts();
        List<Profile> finalProfiles = campaign.getProfiles();
        List<Client> finalClients = new ArrayList<>();

        List<Client> allClients = clientService.getAllClients();
        List<Product> allProducts = productService.getAllProducts();
        List<Store> allStores = storeService.getAllStores();
        List<Profile> allProfiles = profileService.getAllProfiles();
        List<ProductCategory> allProductCategories = productCategoryService.getAllProductsCategories();

        List<String> eventKeywords = campaign.getEvent().getKeywords();

        Set<Product> productsFromEvent = allProducts.stream().filter(
                product -> eventKeywords.stream()
                        .anyMatch(keyword -> keyword.equals(product.getKeywords()))
        ).collect(Collectors.toSet());

        finalProducts.addAll(productsFromEvent);

        Set<Store> storesFromEvent = new HashSet<>();

        for (Store store : allStores) {

            productCategoryLoop:
            for (ProductCategory productCategory : store.getProductCategories()) {
                for (String catKeyword : productCategory.getKeywords()) {
                    for (String keyword : eventKeywords) {
                        if (catKeyword.equals(keyword)) {
                            storesFromEvent.add(store);
                            break productCategoryLoop;
                        }
                    }
                }
            }
        }

        finalStores.addAll(storesFromEvent);

        Set<ProductCategory> productCategoriesFromEvent = new HashSet<>();

        for (ProductCategory productCategory : allProductCategories) {
            catKeywordLoop:
            for (String catKeyword : productCategory.getKeywords()) {
                for (String keyword : eventKeywords) {
                    if (catKeyword.equals(keyword)) {
                        productCategoriesFromEvent.add(productCategory);
                        break catKeywordLoop;
                    }
                }
            }
        }

        for (Profile profile : allProfiles) {
            productCatLoop:
            for (ProductCategory profileProductCategory : profile.getProductCategories()) {
                for (ProductCategory productCategory : productCategoriesFromEvent) {
                    if (profileProductCategory.equals(productCategory)) {
                        finalProfiles.add(profile);
                        break productCatLoop;
                    }
                }
            }
        }

        for (Client client : allClients) {
            Profile major = client.getMajor();
            Profile minor = client.getMinor();
            if (finalProfiles.contains(major) || finalProfiles.contains(minor)) {
                finalClients.add(client);
            }
        }

        logger.info("----------------------------------CAMPAIGN ALGORITHM RESULT----------------------------------");
        logger.info("Nombre de magasin(s) : " + finalStores.size() + " sur " + allStores.size());
        logger.info("Nombre de produits(s) : " + finalProducts.size() + " sur " + allProducts.size());
        logger.info("Nombre de profile(s) : " + finalProfiles.size() + " sur " + allProfiles.size());
        logger.info("Nombre de client(s) : " + finalClients.size() + " sur " + allClients.size());
        logger.info("--------------------------------------------------------------------------------------------");

        recommenderSystemService.startRecommendationsEngine(finalClients);
    }
}