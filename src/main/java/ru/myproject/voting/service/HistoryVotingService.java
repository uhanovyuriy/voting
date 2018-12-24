package ru.myproject.voting.service;

import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;

import java.time.LocalDate;
import java.util.List;

public interface HistoryVotingService {

    HistoryVoting createOrUpdate(int userId, int restaurantId);

    void delete(int id);

    HistoryVoting get(int id);

    List<HistoryVoting> getAll();

//    List<Restaurant> resultVotingToDay(LocalDate date);
}
