package ru.myproject.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.voting.model.HistoryVoting;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.model.User;
import ru.myproject.voting.repository.HistoryVotingCrudRepository;
import ru.myproject.voting.repository.RestaurantCrudRepository;
import ru.myproject.voting.repository.UserCrudRepository;
import ru.myproject.voting.util.exception.IncorrectTimeVoting;
import ru.myproject.voting.util.exception.NotFoundException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
public class HistoryVotingServiceImpl implements HistoryVotingService {

    private HistoryVotingCrudRepository repository;

    private RestaurantCrudRepository restaurantRepository;

    private UserCrudRepository userRepository;

    @Autowired
    public HistoryVotingServiceImpl(HistoryVotingCrudRepository repository, RestaurantCrudRepository restaurantRepository,
                                    UserCrudRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HistoryVoting createOrUpdate(int userId, int restaurantId) {
        LocalDateTime currentDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        if (!isCorrectTime(currentDateTime)) {
            throw new IncorrectTimeVoting("Incorrect time for voting");
        }

        Optional<HistoryVoting> optional = Optional.ofNullable(repository
                .getHistoryVotingByUserIdAndDate(userId, LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.MIN)));
        if (!optional.isPresent()) {
            User user = userRepository.getOne(userId);
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
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public HistoryVoting get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Voting not found"));
    }

    @Override
    public List<HistoryVoting> getAll() {
        return repository.findAll();
    }

    //    @Override
//    public List<Restaurant> resultVotingToDay(LocalDate date) {
//        AtomicLong maxVoices = new AtomicLong();
//        List<Restaurant> listResult = new ArrayList<>();
//        List<HistoryVoting> list = repository.getAllByDateTimeVoting_Date(date);
//        list.stream()
//                .collect(Collectors.groupingBy(HistoryVoting::getRestaurant, Collectors.counting()))
//                .entrySet().stream()
//                .sorted(Map.Entry.<Restaurant, Long>comparingByValue().reversed())
//                .forEach(e -> {
//                    if (listResult.isEmpty()) {
//                        listResult.add(e.getKey());
//                        maxVoices.set(e.getValue());
//                    }
//                    if (!listResult.isEmpty() && e.getValue() == maxVoices.get()) {
//                        listResult.add(e.getKey());
//                    }
//                });
//        return listResult;
//    }

    private boolean isCorrectTime(LocalDateTime ldt) {
        LocalTime startTime = LocalTime.MIN;
        LocalTime endTime = LocalTime.of(18, 0);
        return ldt.toLocalTime().isAfter(startTime) && ldt.toLocalTime().isBefore(endTime);
    }
}
