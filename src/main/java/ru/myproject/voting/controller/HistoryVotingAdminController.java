package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.repository.HistoryVotingCrudRepository;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = HistoryVotingAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotingAdminController {

    final static String REST_URL = "voting/rest/users/admin/voting";

    private final HistoryVotingCrudRepository repository;

    @Autowired
    public HistoryVotingAdminController(HistoryVotingCrudRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        if (repository.deleteById(id) != 1) {
            throw new NotFoundException("Voting " + id + " not found");
        }
    }

    @GetMapping(value = "/{id}")
    public HistoryVoting get(@PathVariable("id") int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("History with " + id + " not found"));
    }

    @GetMapping
    public List<HistoryVoting> getAll() {
        return repository.findAll();
    }
}
