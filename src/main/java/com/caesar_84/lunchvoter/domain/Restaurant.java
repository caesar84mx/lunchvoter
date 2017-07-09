package com.caesar_84.lunchvoter.domain;

import com.caesar_84.lunchvoter.domain.basic_abstracts.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public Restaurant() { }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
