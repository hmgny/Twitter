package com.workintech.twitter.service;

import com.workintech.twitter.dto.UserResponseDto;
import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.TwitterException;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(String userName, String password) {

        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if (optionalUser.isPresent()) {
            throw new TwitterException("User already registered!", HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getUserName());
    }
}