package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.User;


public interface UserCrudRepository extends JpaRepository<User, Integer> {
}
