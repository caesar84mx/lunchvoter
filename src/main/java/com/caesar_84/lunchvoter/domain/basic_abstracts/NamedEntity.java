package com.caesar_84.lunchvoter.domain.basic_abstracts;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * For entities having a name field.
 */
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    public NamedEntity() {}

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "NamedEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
