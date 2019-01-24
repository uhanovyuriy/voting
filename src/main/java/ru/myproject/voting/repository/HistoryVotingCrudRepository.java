package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.HistoryVoting;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface HistoryVotingCrudRepository extends JpaRepository<HistoryVoting, Integer> {

    @Query("SELECT h FROM HistoryVoting h WHERE h.user.id=:userId AND h.dateVoting=:date")
    HistoryVoting getByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    List<HistoryVoting> getHistoryVotingsByDateVotingBetween(LocalDate startDate, LocalDate endDate);
}
