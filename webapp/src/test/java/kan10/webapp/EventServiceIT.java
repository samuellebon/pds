package kan10.webapp;

import kan10.entities.Event;
import kan10.service.EventService;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

public class EventServiceIT extends AbstractIT {

    @Inject
    EventService service;

    private Event event;

    @Before
    public void setUp() {
        event = new Event("label","description");
    }

    @Test
    public void createEvent_OK() {

        assertTrue(service.createEvent(event));

        List<Event> events = service.getAllEvents();
        assertEquals(1, events.size());
        Event actual = events.get(0);

        assertEquals(event.getLabel(), actual.getLabel());
        assertEquals(event.getDescription(), actual.getDescription());

    }

    @Test
    public void createEvent_KO_invalid_Label() {

        event.setLabel("##");

        assertFalse(service.createEvent(event));
        assertEquals(0, service.getAllEvents().size());

    }

    @Test
    public void createEvent_KO_null_label() {

        event.setLabel(null);

        assertFalse(service.createEvent(event));
        assertEquals(0, service.getAllEvents().size());

    }

    @Test
    public void createEvent_KO_null_description() {

        event.setDescription(null);

        assertFalse(service.createEvent(event));
        assertEquals(0, service.getAllEvents().size());

    }

    @Test
    public void createEvent_KO_already_exist() {

        service.createEvent(event);
        Event duplicate = new Event("label","description");

        assertFalse(service.createEvent(duplicate));
        assertEquals(1, service.getAllEvents().size());

    }

    @Test
    public void createEvent_KO_null() {

        assertFalse(service.createEvent(null));
        assertEquals(0, service.getAllEvents().size());

    }
}
