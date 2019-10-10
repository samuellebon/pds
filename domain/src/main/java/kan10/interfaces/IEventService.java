package kan10.interfaces;

import kan10.entities.Event;

import java.util.List;

public interface IEventService {

    boolean createEvent(Event event);
    List<Event> getAllEvents();

}
