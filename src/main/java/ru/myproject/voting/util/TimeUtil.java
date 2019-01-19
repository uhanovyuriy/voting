package ru.myproject.voting.util;

import ru.myproject.voting.util.exception.IncorrectTime;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtil {

    public static void checkCorrectTimeVoting(LocalDateTime ldt, LocalTime timeEndVoting) {
        if (!ldt.toLocalTime().isBefore(timeEndVoting)) {
            throw new IncorrectTime("Incorrect time for voting");
        }
    }

    public static void checkTimeViewResultVoting(LocalDateTime ldt, LocalTime timeEndVoting) {
        if (ldt.toLocalTime().isBefore(timeEndVoting)) {
            throw new IncorrectTime("Voting is not ended");
        }
    }
}
