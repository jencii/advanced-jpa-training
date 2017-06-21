package training360.advancedjpa.entitygraph;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "listEmployees", query = "select e from Employee e order by e.name"),
})
@NamedEntityGraph(name = "graph.Employee.phones",
        attributeNodes = @NamedAttributeNode(value = "phones"))
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMP_NAME")
    private String name;

    @OneToMany(mappedBy = "employee")
    @OrderBy("type")
    private List<Phone> phones = new ArrayList<>();
    
    @Basic(fetch = FetchType.LAZY)
    //@Lob
    private String cv;

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setEmployee(this);
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
