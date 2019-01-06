package testdata;

import ru.myproject.voting.controller.RestaurantController;
import ru.myproject.voting.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

public class TestDataRestaurant {

    public static final String REST_URL = "http://localhost:8080/" + RestaurantController.REST_URL;

    public static final int RESTAURANT1_ID = START_SEQ;

    public static Restaurant RESTAURANT1 = new Restaurant(null, "restNew", "addressNew");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
