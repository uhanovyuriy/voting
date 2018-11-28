package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserCrudRepository extends JpaRepository<User, Integer> {

    Optional<User> findUsersByEmail(String email);
}
