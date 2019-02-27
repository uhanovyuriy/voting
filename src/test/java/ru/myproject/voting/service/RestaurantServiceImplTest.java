package ru.myproject.voting.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.myproject.voting.TestDataRestaurant.*;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(value = "admin", roles = "ADMIN")
@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "classpath:application.properties", properties = {"spring.cache.type=none"})
public class RestaurantServiceImplTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testCreate() {
        Restaurant created = service.createOrUpdate(createNew);
        List<Restaurant> expected = getRestaurants();
        expected.add(created);
        assertMatch(service.getAll(), expected);
    }

    @Test
    public void testUpdate() {
        Restaurant updated = RESTAURANT1;
        updated.setName("updateName");
        service.createOrUpdate(updated);
        assertMatch(service.get(updated.getId()), updated);
    }

    @Test
    public void testDelete() {
        service.delete(RESTAURANT1.getId());
        List<Restaurant> expected = getRestaurants();
        expected.remove(RESTAURANT1);
        assertMatch(service.getAll(), expected);
    }

    @Test
    public void testDeleteNotFound() {
        assertThatThrownBy(() -> service.delete(START_SEQ)).isInstanceOf(NotFoundException.class);
        assertThatThrownBy(() -> service.delete(START_SEQ)).hasMessage("Not found entity with id=" + START_SEQ);
    }

    @Test
    public void testGet() {
        Restaurant actual = service.get(RESTAURANT1.getId());
        assertMatch(actual, RESTAURANT1);
    }

    @Test
    public void testGetNotFound() {
        assertThatThrownBy(() -> service.get(START_SEQ)).isInstanceOf(NotFoundException.class);
        assertThatThrownBy(() -> service.get(START_SEQ)).hasMessage("Restaurant " + START_SEQ + " not found");
    }

    @Test
    public void testGetAll() {
        assertMatch(service.getAll(), getRestaurants());
    }
}