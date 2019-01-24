package ru.myproject.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "history_voting")
public class HistoryVoting extends AbstractBaseEntity {

    @Column(name = "date_voting", nullable = false)
    @NotNull
    private LocalDate dateVoting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public HistoryVoting() {
    }

    public HistoryVoting(Integer id, LocalDate dateVoting, User user, Restaurant restaurant) {
        super.id = id;
        this.dateVoting = dateVoting;
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

    public LocalDate getDateVoting() {
        return dateVoting;
    }

    public void setDateVoting(LocalDate dateVoting) {
        this.dateVoting = dateVoting;
    }

    @Override
    public String toString() {
        return "HistoryVoting{" +
                "dateTimeVoting=" + dateVoting +
                ", userId=" + user.getId() +
                ", restaurantId=" + restaurant.getId() +
                '}';
    }
}
