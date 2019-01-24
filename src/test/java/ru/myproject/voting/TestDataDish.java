package ru.myproject.voting;

import ru.myproject.voting.model.Dish;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.TestDataRestaurant.RESTAURANT1;

public class TestDataDish {

    private static final LocalDate CREATED = LocalDate.of(2018, 12, 28);

    public static final Dish NEW_DISH = new Dish(null, "newDish", 600, CREATED, RESTAURANT1);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
