package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;
import ru.myproject.voting.service.HistoryVotingService;
import ru.myproject.voting.service.RestaurantService;
import ru.myproject.voting.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "voting/rest/voting", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotingController {

    private HistoryVotingService historyVotingService;

    private RestaurantService restaurantService;

    @Autowired
    public HistoryVotingController(HistoryVotingService historyVotingService, RestaurantService restaurantService) {
        this.historyVotingService = historyVotingService;
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAll();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HistoryVoting create(@RequestParam("userId") int userId, @RequestParam("restaurantId") int restaurantId) {
        return historyVotingService.createOrUpdate(userId, restaurantId);
    }

    @GetMapping(value = "{id}")
    public HistoryVoting get(@PathVariable("id") int id) {
        return historyVotingService.get(id);
    }

    @GetMapping(value = "/")
    public List<HistoryVoting> getAll() {
        return historyVotingService.getAll();
    }

//    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Restaurant> resultVotingToDay(@RequestParam LocalDate date) {
//        return historyVotingService.resultVotingToDay(date);
//    }
}
