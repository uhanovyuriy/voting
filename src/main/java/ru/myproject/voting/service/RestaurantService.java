package ru.myproject.voting.service;

import org.springframework.security.access.annotation.Secured;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    @Secured("ROLE_ADMIN")
    Restaurant createOrUpdate(Restaurant restaurant);

    @Secured("ROLE_ADMIN")
    void delete(int id);

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    Restaurant get(int id);

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    List<Restaurant> getAll();

    @Secured("ROLE_ADMIN")
    Dish createOrUpdateDish(Dish dish, int id);

    @Secured("ROLE_ADMIN")
    void deleteDish(int id, int dishId);

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    List<Dish> getDishesForRestaurantBetweenDates(int id, LocalDate startDate, LocalDate endDate);
}
