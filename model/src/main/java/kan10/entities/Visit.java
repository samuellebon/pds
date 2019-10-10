package kan10.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entity class to manage Visit object from database
 * @author Suriya
 */
@Entity
@Table(name="visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade= CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_store")
    private Store storeVisit;

    @ManyToOne(cascade= CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="id_client")
    private Client clientVisit;

    @Column(name="startdate")
    private Timestamp startDate;

    @Column(name="enddate")
    private Timestamp endDate;


    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", storeVisit=" + storeVisit +
                ", customerVisit=" + clientVisit +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Visit(Store storeVisit, Client clientVisit, Timestamp startDate, Timestamp endDate) {
        this.storeVisit = storeVisit;
        this.clientVisit = clientVisit;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Store getStoreVisit() {
        return storeVisit;
    }

    public void setStoreVisit(Store storeVisit) {
        this.storeVisit = storeVisit;
    }

    public Client getClientVisit() {
        return clientVisit;
    }

    public void setClientVisit(Client clientVisit) {
        this.clientVisit = clientVisit;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
