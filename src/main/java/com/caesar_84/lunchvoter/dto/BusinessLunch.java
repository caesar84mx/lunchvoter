package com.caesar_84.lunchvoter.dto;

import com.caesar_84.lunchvoter.domain.Meal;
import com.caesar_84.lunchvoter.domain.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * A full menu response class.
 */
public class BusinessLunch {
    private Integer id;

    private Restaurant restaurant;

    private LocalDate published;

    private int price;

    private List<Meal> items;

    public BusinessLunch() {}

    public BusinessLunch(@JsonProperty Integer id,
                         @JsonProperty Restaurant restaurant,
                         @JsonProperty LocalDate published,
                         @JsonProperty int price,
                         @JsonProperty List<Meal> items) {
        this.id = id;
        this.restaurant = restaurant;
        this.published = published;
        this.price = price;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Meal> getItems() {
        return items;
    }

    public void setItems(List<Meal> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BusinessLunch{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", published=" + published +
                ", price=" + price +
                ", items=" + items +
                '}';
    }
}
