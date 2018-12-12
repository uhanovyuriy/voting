package ru.myproject.voting.service;

import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    List<Dish> createOrUpdateMenu(List<Dish> menu, int restaurantId);

    void deleteMenu(int restaurantId);
}
