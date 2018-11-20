package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.Restaurant;


public interface RestaurantCrudRepository extends JpaRepository<Restaurant, Integer> {
}
