package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

import static ru.myproject.voting.util.ValidationUtil.assureIdConsistent;
import static ru.myproject.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    final static String REST_URL = "voting/rest/users/restaurants";

    private final RestaurantCrudRepository repository;

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantCrudRepository repository, RestaurantService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        Restaurant created = service.createOrUpdate(restaurant);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        service.createOrUpdate(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> createMenu(@RequestBody List<Dish> menu, @PathVariable("id") int restaurantId) {
        return service.createOrUpdateMenu(menu, restaurantId);
    }

    @PutMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenu(@RequestBody List<Dish> menu, @PathVariable("id") int restaurantId) {
        service.createOrUpdateMenu(menu, restaurantId);
    }

    @DeleteMapping(value = "/{id}/menu")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable("id") int restaurantId) {
        service.deleteMenu(restaurantId);
    }
}
