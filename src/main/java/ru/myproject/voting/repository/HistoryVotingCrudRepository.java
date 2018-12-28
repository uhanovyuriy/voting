package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.HistoryVoting;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface HistoryVotingCrudRepository extends JpaRepository<HistoryVoting, Integer> {

    @Query("SELECT h FROM HistoryVoting h WHERE h.user.id=:userId AND h.dateTimeVoting>:dateTime")
    HistoryVoting getByUserIdAndDate(@Param("userId") int userId, @Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT h FROM HistoryVoting h WHERE h.dateTimeVoting>:dateTime")
    List<HistoryVoting> getAllToCurrentDay(@Param("dateTime") LocalDateTime dateTime);
}
