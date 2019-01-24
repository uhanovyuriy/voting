package ru.myproject.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        super.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
    }

    public Restaurant(Integer id, String name, String address) {
        super.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                "address='" + address + '\'' +
                '}';
    }
}
