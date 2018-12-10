package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private DishCrudRepository dishRepository;

    private RestaurantCrudRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(DishCrudRepository dishRepository, RestaurantCrudRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Restaurant getWithDishes(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAllWithDihes() {
        return null;
    }

    @Override
    public List<Dish> updateMenu(int restaurantId) {
        return null;
    }
}
