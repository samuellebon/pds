package kan10.api;

import kan10.entities.Event;
import kan10.entities.Store;
import kan10.enums.Keywords;
import kan10.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventApi {


    private static EventService eventService;

    static List<String> keywords = Keywords.getAll();
    List<String> selectedKeywords = new ArrayList<>();

    @Inject
    public EventApi(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public void createEvent(@RequestBody Event event) {
        event.setKeywords(selectedKeywords);
        eventService.createEvent(event);
        selectedKeywords.clear();
    }

    @GetMapping("/tags")
    public List<String> getKeywords() {
        return Keywords.getAll();
    }

    @PutMapping("/addKeyword")
    public void addKeywordApi(@RequestParam String name) {
        Optional<String> e = keywords.stream().filter(strKey -> strKey.equals(name)).findFirst();
        e.ifPresent(strKey -> selectedKeywords.add(strKey));
    }

    @PutMapping("/removeKeyword")
    public void removeKeywordApi(@RequestParam String name) {
        selectedKeywords.removeIf(strKey -> strKey.equals(name));
    }
}
