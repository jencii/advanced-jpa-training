package training360.advancedjpa.descartes;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EmployeeServiceTest extends TestBase {

    @Before
    public void storeEmployees() {
        EntityManager entityManager = createEntityManager();
        EmployeeService employeeService = new EmployeeService(entityManager);
        entityManager.getTransaction().begin();

        Employee employee1 = new Employee();
        employee1.setName("test1");
        employee1.setCv("cv1");
        employee1.addPhone(new Phone("home", "1234"));
        employee1.addPhone(new Phone("mobile", "5678"));
        employee1.addAddress(new Address("Budapest", "1234"));
        employee1.addAddress(new Address("Etyek", "5678"));
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setName("test2");
        employee2.setCv("cv2");
        employee2.addPhone(new Phone("home", "4321"));
        employee2.addPhone(new Phone("mobile", "8765"));
        employee2.addAddress(new Address("Budapest", "4321"));
        employee2.addAddress(new Address("Etyek", "8765"));
        employeeService.saveEmployee(employee2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void listEmployees() {
        EntityManager entityManager = createEntityManager();
        EmployeeService employeeService = new EmployeeService(entityManager);
        List<Employee> employees =
                employeeService.listEmployees();
        entityManager.close();
        assertThat(employees.size(), equalTo(2));
        assertThat(employees.get(0).getName(), equalTo("test1"));
        assertThat(employees.get(0).getPhones().get(0).getType(), equalTo("home"));
        assertThat(employees.get(0).getAddresses().get(0).getCity(), equalTo("Budapest"));
    }

}
