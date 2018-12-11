package ru.myproject.voting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.service.RestaurantService;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

import static ru.myproject.voting.util.ValidationUtil.assureIdConsistent;
import static ru.myproject.voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "voting/rest/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private RestaurantCrudRepository repository;

    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantCrudRepository repository, RestaurantService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        LOGGER.info("create {}", restaurant);
        Restaurant created = repository.save(restaurant);

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
        repository.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable int id) {
        LOGGER.info("get {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }

    @GetMapping
    public List<Restaurant> getAll() {
        LOGGER.info("getAll");
        return repository.findAll();
    }
}
