package kan10.service;

import kan10.dao.CampaignDao;
import kan10.entities.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

    @Mock
    private CampaignDao campaignDao;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private EventService eventService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private CampaignService service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Campaign campaign;

    @Before
    public void setUp() {
        Event event = new Event("label","description");
        campaign = new Campaign();
        campaign.setEvent(event);
    }

    @Test
    public void createCampaign_OK_no_parameters() {
        CampaignService campaignService = new CampaignService(campaignDao, clientService, productService, eventService);
        CampaignService service = Mockito.spy(campaignService);
        Mockito.doNothing().when(service).campaingAlgorithm(campaign);
        assertTrue(service.createCampaign(campaign));
    }


    @Test
    public void createCampaign_OK_with_product() {
        CampaignService campaignService = new CampaignService(campaignDao, clientService, productService, eventService);
        CampaignService service = Mockito.spy(campaignService);
        Mockito.doNothing().when(service).campaingAlgorithm(campaign);
        campaign.setProducts(new ArrayList<Product>() {{
            add(new Product());
        }});
        assertTrue(service.createCampaign(campaign));
    }

    @Test
    public void createCampaign_OK_with_store() {
        CampaignService campaignService = new CampaignService(campaignDao, clientService, productService, eventService);
        CampaignService service = Mockito.spy(campaignService);
        Mockito.doNothing().when(service).campaingAlgorithm(campaign);
        campaign.setStores(new ArrayList<Store>() {{
            add(new Store());
        }});
        assertTrue(service.createCampaign(campaign));
    }

    @Test
    public void createCampaign_KO_null() {
        assertFalse(service.createCampaign(null));
    }

    @Test
    public void createCampaign_KO_null_event() {
        campaign.setEvent(null);
        assertFalse(service.createCampaign(campaign));
    }
}