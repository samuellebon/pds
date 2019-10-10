package kan10.service;

import kan10.StringUtil;
import kan10.dao.EventDao;
import kan10.entities.Event;
import kan10.interfaces.IEventService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class EventService implements IEventService {

    private final EventDao eventDao;

    @Inject
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public boolean createEvent(Event event) {
        try {

            if (event == null) return false;
            else if (event.getLabel() == null || event.getDescription() == null) return false;

            if (StringUtil.containsInvalidCharacter(event.getLabel())) return false;

            Event isAlreadyInDb = eventDao.findOneByLabel(event.getLabel());

            if (isAlreadyInDb != null) return false;

            eventDao.save(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }
}