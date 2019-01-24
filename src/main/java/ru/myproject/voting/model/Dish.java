package ru.myproject.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1, max = 999999)
    private Integer price;

    @Column(name = "created", columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate created = LocalDate.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.id, dish.getName(), dish.getPrice(), dish.getCreated(), dish.getRestaurant());
    }

    public Dish(Integer id, String name, Integer price, LocalDate created, Restaurant restaurant) {
        super.id = id;
        this.name = name;
        this.price = price;
        this.created = created;
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", restaurant=" + restaurant +
                '}';
    }
}
