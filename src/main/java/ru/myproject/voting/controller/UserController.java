package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;

import java.util.List;


@RestController
@RequestMapping(value = "/voting/rest/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserCrudRepository repository;

    @Autowired
    public UserController(UserCrudRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<User> getAll() {
        return repository.findAll();
    }
}
