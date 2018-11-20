package testdata;

import ru.myproject.voting.model.HistoryVoting;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.myproject.voting.model.AbstractBaseEntity.START_SEQ;

public class TestDataHistoryVote {
    public static final int HISTORY_VOTE_ID = START_SEQ;

//    public static final HistoryVoting HISTORY_VOTE = new HistoryVoting(HISTORY_VOTE_ID, LocalDateTime.of(2018, Month.NOVEMBER, 27, 10, 00), 5);

    public static void assertMatch(HistoryVoting actual, HistoryVoting expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<HistoryVoting> actual, HistoryVoting... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<HistoryVoting> actual, Iterable<HistoryVoting> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
