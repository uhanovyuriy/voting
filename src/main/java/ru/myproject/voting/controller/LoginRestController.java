package ru.myproject.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/voting/rest")
public class LoginRestController {

    private UserCrudRepository repository;

    @Autowired
    public LoginRestController(UserCrudRepository repository) {
        this.repository = repository;
    }

//    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User get(@AuthenticationPrincipal User user) {
//        return user;
//    }
//
//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User getUser(@AuthenticationPrincipal User user) {
//        return user;
//    }
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public String homePage() {
//        return String.valueOf(System.currentTimeMillis());
//    }

//    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public User createNewUser(@Valid User user, BindingResult bindingResult) {
//        Optional<User> userOptional = repository.findUsersByEmail(user.getEmail());
//
//        if (userOptional.isPresent()) {
//            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
//        }
//
//        return userOptional.get();
//    }
}
