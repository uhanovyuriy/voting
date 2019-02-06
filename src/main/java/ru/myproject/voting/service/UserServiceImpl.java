package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.UserCrudRepository;
import ru.myproject.voting.CustomUserDetails;
import ru.myproject.voting.util.exception.NotFoundException;

import java.util.List;

import static ru.myproject.voting.util.UserUtil.prepareToSave;
import static ru.myproject.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserCrudRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserCrudRepository repository) {
        this.repository = repository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @CacheEvict(allEntries = true)
    @Transactional
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(allEntries = true)
    @Transactional
    @Override
    public void update(User user, int id) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), id);
    }

    @CacheEvict(allEntries = true)
    @Transactional
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return repository.findUsersByEmail(email).orElseThrow(() -> new NotFoundException("User " + email + "not found"));
    }

    @Cacheable(key = "#email")
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        return new CustomUserDetails(user);
    }
}
