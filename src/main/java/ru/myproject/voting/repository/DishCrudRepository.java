package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.Dish;


public interface DishCrudRepository extends JpaRepository<Dish, Integer> {
}
