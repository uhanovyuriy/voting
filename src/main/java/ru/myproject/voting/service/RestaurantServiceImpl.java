package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final DishCrudRepository dishRepository;

    private final RestaurantCrudRepository repository;

    @Autowired
    public RestaurantServiceImpl(DishCrudRepository dishRepository, RestaurantCrudRepository repository) {
        this.dishRepository = dishRepository;
        this.repository = repository;
    }

    @Transactional
    @Override
    public Restaurant createOrUpdate(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<Dish> createOrUpdateMenu(List<Dish> menu, int restaurantId) {
        Restaurant r = repository.getOne(restaurantId);
        menu.forEach(dish -> dish.setRestaurant(r));
        return dishRepository.saveAll(menu);
    }

    @Transactional
    @Override
    public void deleteMenu(int restaurantId) {
        Restaurant r = repository.getOne(restaurantId);
        dishRepository.deleteAllByRestaurant(r);
    }
}
