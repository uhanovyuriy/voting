package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT h FROM HistoryVoting h WHERE  h.dateTimeVoting>:startDateTime AND h.dateTimeVoting<:endDateTime")
    List<HistoryVoting> getAllToCurrentDay(@Param("startDateTime") LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Modifying
    @Transactional
    @Query("DELETE FROM HistoryVoting h WHERE h.id=:id")
    int deleteById(@Param("id") int id);
}
