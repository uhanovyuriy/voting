package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.HistoryVotingCrudRepository;


@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    private HistoryVotingCrudRepository repository;

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public HistoryVoting create(int userId, int restaurantId) {
        return null;
    }

    @Override
    public void update(int userId) {

    }

    @Override
    public Restaurant resultVotingToDay() {
        return null;
    }
}
