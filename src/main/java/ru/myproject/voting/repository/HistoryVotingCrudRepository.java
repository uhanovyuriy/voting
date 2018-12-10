package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.HistoryVoting;


public interface HistoryVotingCrudRepository extends JpaRepository<HistoryVoting, Integer> {
}
