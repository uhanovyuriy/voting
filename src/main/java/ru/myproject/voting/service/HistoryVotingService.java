package ru.myproject.voting.service;

import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;

import java.util.List;

public interface HistoryVotingService {

    HistoryVoting create(int userId, int restaurantId);

    void update(int id);

    Restaurant resultVotingToDay();
}
