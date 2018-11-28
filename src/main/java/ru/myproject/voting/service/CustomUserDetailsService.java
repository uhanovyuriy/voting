package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.CustomUserDetails;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCrudRepository repository;

    @Autowired
    public CustomUserDetailsService(UserCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findUsersByEmail(email).get();
        return new CustomUserDetails(user);
    }
}
