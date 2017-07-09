package com.caesar_84.lunchvoter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

public class LoggedUser extends User {

    private static Integer id;

    public LoggedUser(com.caesar_84.lunchvoter.domain.User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        id = user.getId();
    }

    private static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user =   auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        Objects.requireNonNull(user, "Authorized user not found");
        return user;
    }

    public static int id() {
        return id;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "id=" + id +
                "name=" + getUsername() +
                '}';
    }
}