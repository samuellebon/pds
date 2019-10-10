package kan10.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="event_id")
    private Event event;

    @ElementCollection
    private List<Product> products;

    @ElementCollection
    private List<Profile> profiles;

    @ElementCollection
    private List<Store> stores;

    public Campaign(Event event, List<Product> products, List<Profile> profiles, List<Store> stores) {
        this.event = event;
        this.products = products;
        this.profiles = profiles;
        this.stores = stores;
    }

    public Campaign() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}