package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.service.HistoryVotingService;
import ru.myproject.voting.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = "voting/rest/voting")
public class HistoryVotingController {

    private HistoryVotingService historyVotingService;

    private RestaurantService restaurantService;

    @Autowired
    public HistoryVotingController(HistoryVotingService historyVotingService, RestaurantService restaurantService) {
        this.historyVotingService = historyVotingService;
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping(value = "/create")
    public void create(@RequestParam("userId") int userId, @RequestParam("restaurantId") int restaurantId) {
        historyVotingService.create(userId, restaurantId);
    }

    @PutMapping(value = "/update/{id}")
    public void update(@PathVariable("id") int userId) {
        historyVotingService.update(userId);
    }

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant resultVotingToDay() {
        return historyVotingService.resultVotingToDay();
    }
}
