package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.myproject.voting.model.User;
import ru.myproject.voting.service.UserService;
import ru.myproject.voting.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    static final String REST_URL = "voting/rest/users";

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = service.create(user);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id) {
        ValidationUtil.assureIdConsistent(user, id);
        service.update(user, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/by")
    public User getByMail(@RequestParam("email") String email) {
        return service.getByEmail(email);
    }
}
