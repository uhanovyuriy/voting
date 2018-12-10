package ru.myproject.voting.service;

import org.springframework.stereotype.Service;
import ru.myproject.voting.model.HistoryVoting;

import java.util.List;

@Service
public interface HistoryVotingService {

    HistoryVoting create(HistoryVoting voice);

    HistoryVoting get(int id);

    List<HistoryVoting> getAll();
}
