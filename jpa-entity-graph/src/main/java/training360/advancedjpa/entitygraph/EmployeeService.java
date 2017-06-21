package training360.advancedjpa.entitygraph;

import javax.persistence.*;
import java.util.List;

public class EmployeeService {

    private EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public List<Employee> listEmployees() {
        Query query = em.createNamedQuery("listEmployees", Employee.class);
        query.setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.Employee.phones"));
        List<Employee> employees = query.getResultList();

        /*EntityGraph<Employee> graph = em.createEntityGraph(Employee.class);
        graph.addAttributeNodes("name");
        Subgraph<Phone> subgraph = graph.addSubgraph("phones", Phone.class);
        subgraph.addAttributeNodes("type");
        List<Employee> employees = em.createNamedQuery("listEmployees", Employee.class)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();*/

        return employees;
    }

    public Employee findEmployeeById(long id) {
        return em.find(Employee.class, id);
        //return em.createNamedQuery("findEmployeeById", Employee.class).setParameter("id", id).getSingleResult();

//        Map hints = new HashMap();
//        hints.put("javax.persistence.fetchgraph", em.getEntityGraph("graph.Employee.phones"));
//        return em.find(Employee.class, id, hints);
    }

    public void saveEmployee(String name, String cv, String... phones) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setCv(cv);
        for (int i = 0; i < phones.length; i += 2) {
            Phone phone = new Phone(phones[i], phones[i + 1]);
            employee.addPhone(phone);
            em.persist(phone);
        }
        em.persist(employee);
        em.merge(employee);
    }
}
