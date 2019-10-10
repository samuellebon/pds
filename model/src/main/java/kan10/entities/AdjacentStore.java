package kan10.entities;

import javax.persistence.*;

/**
 * @author IliesFaddaoui
 * @version 1.0
 */
@Entity
@Table(name="AdjacentStore")
public class AdjacentStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_concerned_store")
    private Store concernedStore;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_adjacent_store")
    private Store adjacentStore;

    @Column(name="distance")
    private float distance;

    public AdjacentStore(){}
    public AdjacentStore(Store concernedStore, Store adjacentStore, float distance) {
        this.concernedStore = concernedStore;
        this.adjacentStore = adjacentStore;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getConcernedStore() {
        return concernedStore;
    }

    public void setConcernedStore(Store concernedStore) {
        this.concernedStore = concernedStore;
    }

    public Store getAdjacentStore() {
        return adjacentStore;
    }

    public void setAdjacentStore(Store adjacentStore) {
        this.adjacentStore = adjacentStore;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
