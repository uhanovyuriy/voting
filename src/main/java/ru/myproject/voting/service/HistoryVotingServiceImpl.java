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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static ru.myproject.voting.util.TimeUtil.checkCorrectTimeVoting;
import static ru.myproject.voting.util.TimeUtil.checkTimeViewResultVoting;


@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    @Value("${time.end.voting}")
    private LocalTime timeEndVoting;

    private final HistoryVotingCrudRepository repository;

    private final RestaurantCrudRepository restaurantRepository;

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingCrudRepository repository, RestaurantCrudRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @Override
    public HistoryVoting createOrUpdate(User user, int restaurantId) {
        Assert.notNull(user, "user must not be null");
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        checkCorrectTimeVoting(currentDateTime, timeEndVoting);

        Optional<HistoryVoting> optional = Optional.ofNullable(repository
                .getByUserIdAndDate(user.getId(), LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.MIN)));
        if (!optional.isPresent()) {
            Restaurant restaurant = restaurantRepository.getOne(restaurantId);
            return repository.save(new HistoryVoting(null, currentDateTime, user, restaurant));
        } else {
            optional.ifPresent(h -> {
                h.setRestaurant(restaurantRepository.getOne(restaurantId));
                h.setDateTimeVoting(currentDateTime);
            });
            return repository.save(optional.get());
        }
    }

    @Override
    public List<Restaurant> resultVotingToDay(LocalDateTime dateTime) {
        Assert.notNull(dateTime, "dateTime must not be null");
        checkTimeViewResultVoting(dateTime, timeEndVoting);

        AtomicLong maxVoices = new AtomicLong();
        List<Restaurant> listResult = new ArrayList<>();
        List<HistoryVoting> list = repository.getAllToCurrentDay(LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MIN),
                LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX));
        list.stream()
                .collect(Collectors.groupingBy(HistoryVoting::getRestaurant, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Restaurant, Long>comparingByValue().reversed())
                .forEach(e -> {
                    if (listResult.isEmpty()) {
                        Restaurant top = new Restaurant(e.getKey());
                        listResult.add(top);
                        maxVoices.set(e.getValue());
                    } else {
                        if (e.getValue() == maxVoices.get()) {
                            Restaurant top = new Restaurant(e.getKey());
                            listResult.add(top);
                        }
                    }
                });
        return listResult;
    }
}
