package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.HistoryVoting;


public interface HistoryVoteCrudRepository extends JpaRepository<HistoryVoting, Integer> {
}
