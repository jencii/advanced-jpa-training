package training360.advancedjpa.lazy;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestBase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void initDatabase()  {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();

        Map<String, Object> properties = new HashMap<>();
        properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, dataSource);
        entityManagerFactory = Persistence.createEntityManagerFactory("pu", properties);
    }

    @After
    public void closeDatabase() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("drop all objects").executeUpdate();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    protected EntityManager createEntityManager() {
        Map p = Collections.singletonMap("persistence-context.close-on-commit", true);
        return entityManagerFactory.createEntityManager(p);
    }

}
