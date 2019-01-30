package ru.myproject.voting.service;

import org.springframework.security.access.annotation.Secured;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.User;

import java.time.LocalDate;
import java.util.List;


public interface HistoryVotingService {

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    void createOrUpdate(User user, int restaurantId);

    @Secured("ROLE_ADMIN")
    List<HistoryVoting> getBetween(LocalDate startDate, LocalDate endDate);
}
