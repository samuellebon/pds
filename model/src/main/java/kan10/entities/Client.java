package kan10.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private String email;

    private String phone;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="major_profile")
    private Profile major;

    @ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name="minor_profile")
    private Profile minor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders;


    public Client(int code, String email, String phone) {
        this.code = code;
        this.email = email;
        this.phone = phone;
        this.orders = new HashSet<>();
    }

    public Client(int code, String email, String phone, Profile major, Profile minor) {
        this.code = code;
        this.email = email;
        this.phone = phone;
        this.major = major;
        this.minor = minor;
        this.orders = new HashSet<>();
    }

    public Client () { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Profile getMajor() {
        return major;
    }

    public void setMajor(Profile major) {
        this.major = major;
    }

    public Profile getMinor() {
        return minor;
    }

    public void setMinor(Profile minor) {
        this.minor = minor;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }
}
