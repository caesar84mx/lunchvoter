package com.caesar_84.lunchvoter.domain.basic_abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

/**
 * Basic entity for domain classes.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity implements Persistable<Integer> {
    public static final int GLOBAL_SEQUENCE = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = GLOBAL_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    public BaseEntity() {}

    public BaseEntity(Integer id) { this.id = id; }

    @JsonIgnore
    public boolean isNew() { return id == null; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || !getClass().equals(Hibernate.getClass(obj))) {return false;}

        BaseEntity another = (BaseEntity) obj;

        return getId() != null && getId().equals(another.getId());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
