package training360.advancedjpa.descartes;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeService {

    private EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public List<Employee> listEmployees() {
        // List<Employee> employees = em.createNamedQuery("listEmployees", Employee.class).getResultList();
//        List<Employee> employees = em.createNamedQuery("listEmployeesWithPhonesAndAddresses", Employee.class).getResultList();
        em.createNamedQuery("listEmployeesWithPhones").getResultList();
        List<Employee> employees = em.createNamedQuery("listEmployeesWithAddresses").getResultList();
        return employees;
    }

    public Employee findEmployeeById(long id) {
        return em.find(Employee.class, id);
        //return em.createNamedQuery("findEmployeeById", Employee.class).setParameter("id", id).getSingleResult();
    }

    public void saveEmployee(Employee employee) {
        em.persist(employee);
    }
}
