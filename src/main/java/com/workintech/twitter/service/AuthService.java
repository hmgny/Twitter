package com.workintech.twitter.service;

import com.workintech.twitter.entity.User;

public interface AuthService {
    public User register(String userName, String password);
    public String login(String userName, String password);
}