package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"restaurants"})
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
    @CacheEvict(allEntries = true)
    public Restaurant createOrUpdate(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    @Override
    @CacheEvict(allEntries = true)
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant " + id + " not found"));
    }

    @Override
    @Cacheable
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    @CacheEvict(allEntries = true)
    public List<Dish> createOrUpdateMenu(List<Dish> menu, int restaurantId) {
        Restaurant r = repository.getOne(restaurantId);
        menu.forEach(dish -> dish.setRestaurant(r));
        return dishRepository.saveAll(menu);
    }

    @Transactional
    @Override
    @CacheEvict(allEntries = true)
    public void deleteMenu(int restaurantId) {
        Restaurant r = repository.getOne(restaurantId);
        dishRepository.deleteAllByRestaurant(r);
    }
}
