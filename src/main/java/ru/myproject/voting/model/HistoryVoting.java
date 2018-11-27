package ru.myproject.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_voting")
public class HistoryVoting extends AbstractBaseEntity{

    @Column(name = "date_time_voting", nullable = false)
    @NotBlank
    private LocalDateTime dateTimeVoting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotBlank
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotBlank
    private Restaurant restaurant;

    public HistoryVoting() {
    }

    public HistoryVoting(Integer id, LocalDateTime dateTimeVoting, User user, Restaurant restaurant) {
        super.id = id;
        this.dateTimeVoting = dateTimeVoting;
        this.user = user;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTimeVoting() {
        return dateTimeVoting;
    }

    public void setDateTimeVoting(LocalDateTime dateTimeVoting) {
        this.dateTimeVoting = dateTimeVoting;
    }

    @Override
    public String toString() {
        return "HistoryVoting{" +
                "dateTimeVoting=" + dateTimeVoting +
                ", userName=" + user.getName() +
                ", restaurant_name=" + restaurant.getName() +
                '}';
    }
}
