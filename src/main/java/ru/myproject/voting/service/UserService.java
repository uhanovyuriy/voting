package ru.myproject.voting.service;

import ru.myproject.voting.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user, int id);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);
}
