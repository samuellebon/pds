package kan10.service;

import kan10.dao.EventDao;
import kan10.entities.Event;
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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    EventDao eventDao;

    @InjectMocks
    private EventService service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Event event;

    @Before
    public void setUp() {
        event = new Event("label","description");
    }

    @Test
    public void createEvent_OK() {
        assertTrue(service.createEvent(event));
    }

    @Test
    public void createEvent_KO_invalid_Label() {
        event.setLabel("##");
        assertFalse(service.createEvent(event));
    }

    @Test
    public void createEvent_KO_null_label() {
        event.setLabel(null);
        assertFalse(service.createEvent(event));
    }

    @Test
    public void createEvent_KO_null_description() {
        event.setDescription(null);
        assertFalse(service.createEvent(event));
    }

    @Test
    public void createEvent_KO_already_exist() {
        Event eventIndDb = new Event("label","description");
        Mockito.when(eventDao.findOneByLabel("label")).thenReturn(eventIndDb);
        service.createEvent(eventIndDb);
        assertFalse(service.createEvent(event));
    }

    @Test
    public void createEvent_KO_null() {
        assertFalse(service.createEvent(null));
    }

    @Test
    public void testGetAllEvents_OK() {

        List<Event> eventList = new ArrayList<Event>(){{
            add(new Event("label1","description1"));
            add(new Event("label2","description2"));
            add(new Event("label3","description3"));
            add(new Event("label4","description4"));
            add(new Event("label5","description5"));
        }};

        when(eventDao.findAll()).thenReturn(eventList);

        List<Event> events = service.getAllEvents();

        assertNotNull(events);
        assertEquals(5, events.size());
    }

}