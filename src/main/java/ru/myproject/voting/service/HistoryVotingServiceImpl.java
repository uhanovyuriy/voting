package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.HistoryVotingCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.util.exception.ErrorToVoting;
import ru.myproject.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    @Value("${time.end.voice.change}")
    private LocalTime timeEndVoiceChange;

    private final HistoryVotingCrudRepository repository;

    private final RestaurantCrudRepository restaurantRepository;

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingCrudRepository repository, RestaurantCrudRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @Override
    public void createOrUpdate(User user, int restaurantId) {
        Assert.notNull(user, "user must not be null");
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        Optional<HistoryVoting> optional = Optional.ofNullable(repository
                .getByUserIdAndDate(user.getId(), currentDateTime.toLocalDate()));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant " + restaurantId + " not found"));
        if (!optional.isPresent()) {
            repository.save(new HistoryVoting(null, currentDateTime.toLocalDate(), user, restaurant));
        } else if (currentDateTime.toLocalTime().isBefore(timeEndVoiceChange)) {
            HistoryVoting hv = optional.get();
            hv.setRestaurant(restaurant);
            hv.setDateVoting(currentDateTime.toLocalDate());
            repository.save(hv);
        } else {
            throw new ErrorToVoting("You have already voted, and the time to change your mind is over");
        }
    }

    @Override
    public List<HistoryVoting> getBetween(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");

        return repository.getHistoryVotingsByDateVotingBetween(startDate, endDate).stream()
                .peek(hv -> {
                    hv.setRestaurant(new Restaurant(hv.getRestaurant()));
                    hv.setUser(new User(hv.getUser()));
                })
                .collect(toList());
    }
}
