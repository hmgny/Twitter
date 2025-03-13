package com.workintech.twitter.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegisterRequestDto {

    public RegisterRequestDto(){}

    public RegisterRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @NotBlank
    @NotNull
    @Email
    private String userName;

    @NotNull
    @NotBlank
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
