package training360.advancedjpa.descartes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "listEmployees", query = "select e from Employee e order by e.name"),
        @NamedQuery(name = "listEmployeesWithPhonesAndAddresses", query = "select distinct e from Employee e join fetch e.phones join fetch e.addresses"),
        @NamedQuery(name = "listEmployeesWithPhones", query = "select distinct e from Employee e join fetch e.phones p order by e.name, p.type"),
        @NamedQuery(name = "listEmployeesWithAddresses", query = "select distinct e from Employee e join fetch e.addresses a order by e.name, a.city"),
})
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMP_NAME")
    private String name;

    //, fetch = FetchType.EAGER
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @OrderBy("type")
    private List<Phone> phones = new ArrayList<>();

    //, fetch = FetchType.EAGER
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @OrderBy("city")
    private List<Address> addresses = new ArrayList<>();
    
    @Basic(fetch = FetchType.LAZY)
    //@Lob
    private String cv;

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setEmployee(this);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setEmployee(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
