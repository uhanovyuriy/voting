package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.DishCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.myproject.voting.util.ValidationUtil.checkNotFoundWithId;

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
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Transactional
    @Override
    @CacheEvict(allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    @Cacheable
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
    public Dish createOrUpdateDish(Dish dish, int id) {
        Assert.notNull(dish, "dish must not be null");

        dish.setRestaurant(get(id));
        return dishRepository.save(dish);
    }

    @Transactional
    @Override
    @CacheEvict(allEntries = true)
    public void deleteDish(int id, int dishId) {
        checkNotFoundWithId(dishRepository.delete(dishId, id), dishId);
    }

    @Override
    @Cacheable
    public List<Dish> getDishesForRestaurantBetweenDates(int id, LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");

        return dishRepository.getBetween(id, startDate, endDate);
    }
}
