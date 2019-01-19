package ru.myproject.voting.service;

import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryVotingService {

    void createOrUpdate(User user, int restaurantId);

    List<Restaurant> resultVotingToDay(LocalDateTime dateTime);
}
