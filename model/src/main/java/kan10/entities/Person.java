package kan10.entities;

import javax.persistence.*;

@Entity
public class Person {
        @Id

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name="first_name")
        private String first_name;

        @Column(name="last_name")
        private String last_name;

        @Column(name="birth_date")
        private String birth_date;

        @Column(name="address")
        private String address;

        @Column(name="gender")
        private String gender;

        @Column(name="telNumber")
        private String telNumber;

        @Column(name="email")
        private String email;

        @Column(name="postal_code")
        private String postal_code;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public Person() {
        }


    public Person(String first_name, String last_name, String birth_date, String address, String gender, String telNumber, String email, String postal_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;

        this.address = address;
        this.gender = gender;
        this.telNumber = telNumber;
        this.email = email;
        this.postal_code = postal_code;
    }

    public int getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() { return email; }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirs_tname(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                ", postal_code='" + postal_code + '\'' +
                '}';
    }
}



