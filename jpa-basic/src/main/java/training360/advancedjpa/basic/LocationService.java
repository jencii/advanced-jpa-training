package training360.advancedjpa.basic;

import javax.persistence.EntityManager;
import java.util.List;

public class LocationService {

    private EntityManager entityManager;

    public LocationService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveLocation(Location location) {
        entityManager.persist(location);
    }

    public List<Location> listLocations() {
        return entityManager.createQuery("select l from Location l", Location.class).getResultList();
    }
}
