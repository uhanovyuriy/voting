package ru.myproject.voting.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;
import ru.myproject.voting.to.CustomUserDetails;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserCrudRepository repository;

    public UserServiceImpl(UserCrudRepository repository) {
        this .repository = repository;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = repository.findUsersByEmail(email.toLowerCase());
        if (optional.isPresent()) {
            return new CustomUserDetails(optional.get());
        } else throw new NotFoundException("User " + email + " not found in repository");
    }
}
