package com.workintech.twitter.controller;

import com.workintech.twitter.dto.RegisterRequestDto;
import com.workintech.twitter.dto.UserResponseDto;
import com.workintech.twitter.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody RegisterRequestDto registerUser){
        return registerService.register(registerUser.getUserName(),registerUser.getPassword());
    }
}
