package testdata;

import ru.myproject.voting.model.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;
import static testdata.TestDataRestaurant.RESTAURANT1;

public class TestDataDish {
    public static final int DISH_ID = START_SEQ + 8;

    public static final Dish DISH1 = new Dish(DISH_ID, "dishes1", 550, RESTAURANT1);
    public static final Dish DISH2 = new Dish(DISH_ID + 1, "dishes2", 850, RESTAURANT1);
    public static final Dish DISH3 = new Dish(DISH_ID + 2, "dishes3", 1550, RESTAURANT1);

    public static final List<Dish> menu = Arrays.asList(DISH1, DISH2, DISH3);

    public static final List<Dish> menuNew = Arrays.asList(new Dish(null, "dishNew1", 100, RESTAURANT1),
            new Dish(null, "dishNew2", 200, RESTAURANT1));

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
