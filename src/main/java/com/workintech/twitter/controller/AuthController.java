package com.workintech.twitter.controller;

import com.workintech.twitter.dto.LoginRequestDto;
import com.workintech.twitter.dto.LoginResponseDto;
import com.workintech.twitter.dto.RegisterRequestDto;
import com.workintech.twitter.dto.RegisterResponseDto;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@Validated @RequestBody RegisterRequestDto request) {

        User user = authService.register(request.getUserName(), request.getPassword());

        return new RegisterResponseDto(user.getUsername(), "User successfully registered!");
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Validated @RequestBody LoginRequestDto request) {
        String token = authService.login(request.getUserName(), request.getPassword());
        return new LoginResponseDto(token, "Login successful!");
    }
}
