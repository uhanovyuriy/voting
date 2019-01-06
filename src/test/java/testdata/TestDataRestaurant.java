package testdata;

import ru.myproject.voting.controller.RestaurantController;
import ru.myproject.voting.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

public class TestDataRestaurant {

    public static final String REST_URL = "http://localhost:8080/" + RestaurantController.REST_URL;

    public static final int RESTAURANT_ID = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "rest1", "address1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "rest2", "address2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID + 2, "rest3", "address3");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT_ID + 3, "rest4", "address4");

    public static Restaurant createNew = new Restaurant(null, "restNew", "addressNew");

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
