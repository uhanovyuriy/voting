package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;


public interface DishCrudRepository extends JpaRepository<Dish, Integer> {

    void deleteAllByRestaurant(Restaurant restaurant);
}
