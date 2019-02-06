package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.CustomUserDetails;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.service.HistoryVotingService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "api/rest/voting", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotingController {

    private final HistoryVotingService service;

    @Autowired
    public HistoryVotingController(HistoryVotingService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@AuthenticationPrincipal CustomUserDetails user, @RequestParam("restaurantId") int restaurantId) {
        service.createOrUpdate(user.getUser(), restaurantId);
    }

    @GetMapping
    public List<HistoryVoting> getBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate startDate,
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate endDate) {
        return service.getBetween(startDate, endDate);
    }
}
