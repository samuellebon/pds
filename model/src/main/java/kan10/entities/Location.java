package kan10.entities;

import javax.persistence.*;


@Entity
@Table(name="Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(name="floorLevel")
    private int floorLevel;

    @Column(name="aisle")
    private String aisle;

    @Column(name="area")
    private double area;

    @Column(name="rent")
    private double rent;

    public Location() {
    }

    public Location(int floorLevel, String aisle, double area, double rent) {
        this.floorLevel = floorLevel;
        this.aisle = aisle;
        this.area = area;
        this.rent = rent;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFloorLevel() { return floorLevel; }

    public void setFloorLevel(int floorLevel) { this.floorLevel = floorLevel;  }


    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }


    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }


    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

}
