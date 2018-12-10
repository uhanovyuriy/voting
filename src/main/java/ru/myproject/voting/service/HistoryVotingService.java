package ru.myproject.voting.service;

import ru.myproject.voting.model.HistoryVoting;

import java.util.List;

public interface HistoryVotingService {

    HistoryVoting create(HistoryVoting voice);

    HistoryVoting get(int id);

    List<HistoryVoting> getAll();
}
