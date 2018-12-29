package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;
import ru.myproject.voting.service.HistoryVotingService;
import ru.myproject.voting.service.RestaurantService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = HistoryVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotingController {

    final static String REST_URL = "voting/rest/users/voting";

    private final HistoryVotingService service;

    private final RestaurantService restaurantService;

    @Autowired
    public HistoryVotingController(HistoryVotingService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryVoting> create(@AuthenticationPrincipal User user, @RequestParam("restaurantId") int restaurantId) {
        HistoryVoting created = service.createOrUpdate(user.getId(), restaurantId);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping(value = "/{id}")
    public HistoryVoting get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping
    public List<HistoryVoting> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/result")
    public List<Restaurant> resultVotingToDay(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime dateTime) {
        return service.resultVotingToDay(dateTime);
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }
}
