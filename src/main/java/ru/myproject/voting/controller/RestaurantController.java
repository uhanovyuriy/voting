package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.Dish;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.service.RestaurantService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.myproject.voting.util.ValidationUtil.assureIdConsistent;
import static ru.myproject.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    public final static String REST_URL = "api/rest/restaurants";

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
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
        return service.getAll();
    }

    @PostMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDish(@Valid @RequestBody Dish dish, @PathVariable("id") int restaurantId) {
        checkNew(dish);
        Dish created = service.createOrUpdateDish(dish, restaurantId);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish updateDish(@RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("dishId") int dishId) {
        assureIdConsistent(dish, dishId);
        return service.createOrUpdateDish(dish, id);
    }

    @DeleteMapping(value = "/{id}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable("id") int id, @PathVariable("dishId") int dishId) {
        service.deleteDish(id, dishId);
    }

    @GetMapping(value = "/{id}/dishes")
    public List<Dish> getDishesForRestaurantBetweenDates(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate startDate,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate endDate,
                                                         @PathVariable("id") int id) {
        return service.getDishesForRestaurantBetweenDates(id, startDate, endDate);
    }
}
