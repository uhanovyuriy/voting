package ru.myproject.voting.service;

import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    HistoryVoting createOrUpdate(int userId, int restaurantId);

    void delete(int id);

    HistoryVoting get(int id);

    List<HistoryVoting> getAll();

    List<Restaurant> resultVotingToDay(LocalDateTime dateTime);
}
