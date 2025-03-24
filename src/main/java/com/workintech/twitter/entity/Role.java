package com.workintech.twitter.entity;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    GUEST;


    @Override
    public String getAuthority() {
        return name();
    }


}
