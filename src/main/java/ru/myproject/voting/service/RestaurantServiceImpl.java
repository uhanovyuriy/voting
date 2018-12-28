package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final DishCrudRepository dishRepository;

    private final RestaurantCrudRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(DishCrudRepository dishRepository, RestaurantCrudRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Dish> createOrUpdateMenu(List<Dish> menu, int restaurantId) {
        Restaurant r = restaurantRepository.getOne(restaurantId);
        menu.forEach(dish -> dish.setRestaurant(r));
        return dishRepository.saveAll(menu);
    }

    @Override
    public void deleteMenu(int restaurantId) {
        Restaurant r = restaurantRepository.getOne(restaurantId);
        dishRepository.deleteAllByRestaurant(r);
    }
}
