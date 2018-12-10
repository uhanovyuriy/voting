package ru.myproject.voting.service;

import org.springframework.stereotype.Service;
import ru.myproject.voting.model.User;

import java.util.List;

@Service
public interface UserService {

    User create(User user);

    User update(User user);

    void delete(int id);

    User get(int id);

    List<User> getAll();
}
