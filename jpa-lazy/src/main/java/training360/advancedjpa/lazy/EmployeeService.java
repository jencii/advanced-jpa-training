package training360.advancedjpa.lazy;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeService {

    private EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public List<Employee> listEmployees() {
        List<Employee> employees = em.createNamedQuery("listEmployees", Employee.class).getResultList();
        return employees;
    }

    public Employee findEmployeeById(long id) {
        return em.find(Employee.class, id);
        //return em.createNamedQuery("findEmployeeById", Employee.class).setParameter("id", id).getSingleResult();
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
