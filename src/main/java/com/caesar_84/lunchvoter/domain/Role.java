package com.caesar_84.lunchvoter.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * User roles.
 */
public enum Role implements GrantedAuthority{
    ROLE_USER,
    ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
