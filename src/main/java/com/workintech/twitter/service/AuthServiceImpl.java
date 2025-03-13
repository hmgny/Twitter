package com.workintech.twitter.service;

import com.workintech.twitter.entity.Role;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exception.TwitterException;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User register(String userName, String password) {

        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if (optionalUser.isPresent()) {
            throw new TwitterException("Email already registered!", HttpStatus.CONFLICT);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public String login(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return "Login successful";
            }
        }
        throw new TwitterException("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}

