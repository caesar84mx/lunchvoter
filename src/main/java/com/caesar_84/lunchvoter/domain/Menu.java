package com.caesar_84.lunchvoter.domain;

import com.caesar_84.lunchvoter.domain.basic_abstracts.BaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price")
    private int price;

    @Column(name = "published")
    private LocalDate published;

    public Menu(){}

    public Menu(Restaurant restaurant, int price, LocalDate published) {//, Set<Meal> menuItems) {
        this(null, restaurant, price, published);//, menuItems);
    }

    public Menu(Integer id, Restaurant restaurant, int price, LocalDate published) {//, Set<Meal> menuItems) {
        super(id);
        this.restaurant = restaurant;
        this.price = price;
        this.published = published;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }


    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + getId() +
                "restaurant=" + restaurant +
                ", published=" + published +
                '}';
    }
}
