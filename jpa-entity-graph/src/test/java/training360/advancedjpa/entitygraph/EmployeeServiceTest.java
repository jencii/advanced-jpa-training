package training360.advancedjpa.entitygraph;

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
        employeeService.saveEmployee("test1", "cv1", "home", "1234", "mobile", "3456");
        employeeService.saveEmployee("test2","cv2", "home", "4321", "mobile", "6543");
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
    }

    @Test
    public void testFindEmployeeById() {
        long id = findFirstId();

        EntityManager entityManager = createEntityManager();
        EmployeeService employeeService = new EmployeeService(entityManager);
        Employee employee = employeeService.findEmployeeById(id);

        assertThat(employee.getName(), equalTo("test1"));
        assertThat(employee.getCv(), equalTo("cv1"));

        entityManager.close();
    }

    private long findFirstId() {
        EntityManager entityManager = createEntityManager();
        EmployeeService employeeService = new EmployeeService(entityManager);
        List<Employee> employees =
                employeeService.listEmployees();
        long id = employees.get(0).getId();
        entityManager.close();
        return id;
    }
}
