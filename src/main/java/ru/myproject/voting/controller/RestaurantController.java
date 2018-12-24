package ru.myproject.voting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.service.RestaurantService;

import java.util.List;

import static ru.myproject.voting.util.ValidationUtil.assureIdConsistent;
import static ru.myproject.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "voting/rest/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private RestaurantCrudRepository repository;

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantCrudRepository repository, RestaurantService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        LOGGER.info("create {}", restaurant);
        Restaurant created = service.create(restaurant);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        LOGGER.info("update {}", restaurant);
        service.update(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        LOGGER.info("delete {}", id);
        service.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable int id) {
        LOGGER.info("get {}", id);
        return service.get(id);
    }

    @GetMapping(value = "/")
    public List<Restaurant> getAll() {
        LOGGER.info("getAll");
        return repository.findAll();
    }

    @PostMapping(value = "/{id}/menu")
    public List<Dish> createMenu(@RequestBody List<Dish> menu, @PathVariable("id") int restaurantId) {
        LOGGER.info("create menu {} for restaurant {}", menu, restaurantId);
        return service.createOrUpdateMenu(menu, restaurantId);
    }

    @PutMapping(value = "/{id}/menu")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenu(@RequestBody List<Dish> menu, @PathVariable("id") int restaurantId) {
        LOGGER.info("update menu {} for restaurant {}", menu, restaurantId);
        service.createOrUpdateMenu(menu, restaurantId);
    }

    @DeleteMapping(value = "/{id}/menu")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable("id") int restaurantId) {
        LOGGER.info("delete menu for restaurant {}", restaurantId);
        service.deleteMenu(restaurantId);
    }
}
