package ru.myproject.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "history_voting")
public class HistoryVoting extends AbstractBaseEntity{

    @Column(name = "dateTime_voting", nullable = false)
    @NotBlank
    private LocalDateTime dateTimeVoting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public HistoryVoting() {
    }

    public HistoryVoting(Integer id, LocalDateTime dateTimeVoting, User user, Restaurant restaurant) {
        super.id = id;
        this.dateTimeVoting = dateTimeVoting;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTimeVoting() {
        return dateTimeVoting;
    }

    public void setDateTimeVoting(LocalDateTime dateTimeVoting) {
        this.dateTimeVoting = dateTimeVoting;
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

    @Override
    public String toString() {
        return "HistoryVoting{" +
                "dateTimeVoting=" + dateTimeVoting +
                ", userId=" + user.getName() +
                ", restaurant_name=" + restaurant.getName() +
                '}';
    }
}
