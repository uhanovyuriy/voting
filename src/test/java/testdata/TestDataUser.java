package testdata;

import ru.myproject.voting.model.Role;
import ru.myproject.voting.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

public class TestDataUser {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN1 = new User(ADMIN_ID, "Admin1", "admin1@gmail.com", "$2a$10$gyIatFXjxzzNvMMmJAYtYOEWk5/Ie1PwCVr8m2hgc0laPX.Yq4U4m", Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles", "meals");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles", "meals").isEqualTo(expected);
    }
}
