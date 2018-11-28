package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;

import java.util.List;

@Service
public class RestaurantService {

    private final DishCrudRepository dishCrudRepository;

    private final RestaurantCrudRepository restaurantCrudRepository;

    @Autowired
    public RestaurantService(DishCrudRepository dishCrudRepository, RestaurantCrudRepository restaurantCrudRepository) {
        this.dishCrudRepository = dishCrudRepository;
        this.restaurantCrudRepository = restaurantCrudRepository;
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantCrudRepository.save(restaurant);
    }

    public List<Dish> createMenu(List<Dish> dishList, Restaurant restaurant) {
        dishList.forEach(dish -> dish.setRestaurant(restaurant));
        return dishCrudRepository.saveAll(dishList);
    }

    public void delete(int id) {
        restaurantCrudRepository.deleteById(id);
    }

    public void deleteMenu(Restaurant restaurant) {
        dishCrudRepository.deleteAllByRestaurant(restaurant);
    }

    public List<Restaurant> getAll() {
        return restaurantCrudRepository.findAll(Sort.by("name", "address"));
    }

}
