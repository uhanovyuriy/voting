package testdata;

import ru.myproject.voting.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

public class TestDataDish {
    public static final int DISH_ID = START_SEQ;

    public static final Dish DISH = new Dish(DISH_ID, "dishes1", 550);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
