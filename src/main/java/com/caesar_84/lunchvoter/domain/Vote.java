package com.caesar_84.lunchvoter.domain;

import com.caesar_84.lunchvoter.domain.basic_abstracts.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    @Column(name = "vote_date")
    private LocalDate voteDate = LocalDate.now();

    public Vote() {}

    public Vote(User user, Menu menu) {
        super(null);
        this.user = user;
        this.menu = menu;
    }

    public Vote(User user, Menu menu, LocalDate voteDate) {
        this(null, user, menu, voteDate);
    }

    public Vote(Integer id, User user, Menu menu, LocalDate voteDate) {
        super(id);
        this.user = user;
        this.menu = menu;
        this.voteDate = voteDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                "user=" + user +
                ", menu=" + menu +
                ", voteDate=" + voteDate +
                '}';
    }
}
