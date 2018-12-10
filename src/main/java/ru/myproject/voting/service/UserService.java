package ru.myproject.voting.service;

import ru.myproject.voting.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    void delete(int id);

    User get(int id);

    List<User> getAll();
}
