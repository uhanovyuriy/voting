package ru.myproject.voting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import testdata.TestDataDish;

import java.util.ArrayList;
import java.util.List;

import static testdata.TestDataDish.menu;
import static testdata.TestDataDish.menuNew;
import static testdata.TestDataRestaurant.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testCreate() {
        Restaurant created = service.createOrUpdate(createNew);
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, created);
    }

    @Test
    public void testGet() {
        Restaurant actual = service.get(RESTAURANT1.getId());
        assertMatch(actual, RESTAURANT1);
    }

    @Test
    public void testCreateMenu() {
        List<Dish> created = service.createOrUpdateMenu(menuNew, RESTAURANT_ID);
        List<Dish> expected = new ArrayList<>(menu);
        expected.addAll(created);
        List<Dish> actual = new ArrayList<>(service.get(RESTAURANT_ID).getDishes());
        TestDataDish.assertMatch(actual, expected);
    }
}