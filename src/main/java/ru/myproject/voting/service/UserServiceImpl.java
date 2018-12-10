package ru.myproject.voting.service;

import org.springframework.stereotype.Service;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
}
