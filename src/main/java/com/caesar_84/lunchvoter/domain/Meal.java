package com.caesar_84.lunchvoter.domain;

import com.caesar_84.lunchvoter.domain.basic_abstracts.NamedEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meals")
public class Meal extends NamedEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    public Meal() {}

    public Meal(String name, int price, Menu menu) { this(null, name, price, menu); }

    public Meal(Integer id, String name, int price, Menu menu) {
        super(id, name);
        this.menu = menu;
    }

    @JsonIgnore
    public Menu getMenu() { return menu; }

    public void setMenu(Menu menu) { this.menu = menu; }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + getId() +
                "name=" + getName() +
                '}';
    }
}
