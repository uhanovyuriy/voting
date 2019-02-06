package ru.myproject.voting;

import ru.myproject.voting.model.User;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public CustomUserDetails(User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
}
