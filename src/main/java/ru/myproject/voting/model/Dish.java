package ru.myproject.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {

    @Column(name = "price", nullable = false)
    @NotBlank
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, Integer price) {
        super.id = id;
        super.name = name;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
                ", name='" + name + '\'' +
                "price=" + price +
                ", restaurant_name=" + restaurant.getName() +
                '}';
    }
}
