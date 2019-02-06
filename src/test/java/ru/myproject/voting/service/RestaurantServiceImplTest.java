package ru.myproject.voting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ru.myproject.voting.TestDataDish;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;

import static ru.myproject.voting.TestDataDish.NEW_DISH;
import static ru.myproject.voting.TestDataRestaurant.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService service;

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void testCreate() {
        Restaurant created = service.createOrUpdate(createNew);
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, created);
    }

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void testGet() {
        Restaurant actual = service.get(RESTAURANT1.getId());
        assertMatch(actual, RESTAURANT1);
    }

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void testCreateDish() {
        Dish actual = service.createOrUpdateDish(NEW_DISH, RESTAURANT_ID);
        Dish expected = new Dish(NEW_DISH);
        expected.setId(actual.getId());
        TestDataDish.assertMatch(actual, expected);
    }
}