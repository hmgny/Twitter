package com.workintech.twitter.service;

import com.workintech.twitter.entity.User;
import java.util.Optional;

public interface UserService {

    public Optional<User> findByUserName(String userName);
}
