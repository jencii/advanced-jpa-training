package training360.advancedjpa.basic;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class LocationServiceTest extends TestBase {

    @Test
    public void testSaveAndList() {
        getEntityManager().getTransaction().begin();
        LocationService locationService = new LocationService(getEntityManager());
        locationService.saveLocation(new Location( "a", 1, 2));
        getEntityManager().getTransaction().commit();
        List<Location> locations = locationService.listLocations();
        assertThat(locations.size(), equalTo(2));
    }

    @Test
    public void test2() {
        getEntityManager().getTransaction().begin();
        LocationService locationService = new LocationService(getEntityManager());
        locationService.saveLocation(new Location( "a", 1, 2));
        locationService.saveLocation(new Location( "a", 1, 2));
        getEntityManager().getTransaction().commit();
        List<Location> locations = locationService.listLocations();
        assertThat(locations.size(), equalTo(3));
    }
}
