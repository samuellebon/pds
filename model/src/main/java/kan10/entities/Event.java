package kan10.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> keywords;

    public Event(String label, String description, List<String> keywords) {
        this.label = label;
        this.description = description;
        this.keywords = keywords;
    }

    public Event(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public Event() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() { return keywords; }

    public void setKeywords (List<String> keywords) { this.keywords = keywords; }
}