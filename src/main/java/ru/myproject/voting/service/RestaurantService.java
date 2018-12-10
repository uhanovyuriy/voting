package ru.myproject.voting.service;

import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id);

    Restaurant getWithDishes(int id);

    List<Restaurant> getAllWithDihes();

    List<Dish> updateMenu(int restaurantId);
}
