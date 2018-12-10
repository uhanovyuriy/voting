package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.repository.HistoryVotingCrudRepository;

import java.util.List;

@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    private HistoryVotingCrudRepository repository;

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public HistoryVoting create(HistoryVoting voice) {
        return null;
    }

    @Override
    public HistoryVoting get(int id) {
        return null;
    }

    @Override
    public List<HistoryVoting> getAll() {
        return null;
    }
}
