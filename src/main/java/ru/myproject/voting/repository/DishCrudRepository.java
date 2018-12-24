package ru.myproject.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;

@Transactional(readOnly = true)
public interface DishCrudRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    void deleteAllByRestaurant(Restaurant restaurant);
}
