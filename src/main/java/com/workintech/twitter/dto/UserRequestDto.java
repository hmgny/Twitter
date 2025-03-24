package com.workintech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequestDto {

    public UserRequestDto(String userName, String password, Long userId) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
    }

    @NotBlank(message = "User name cannot be blank")
    @NotNull(message = "User name cannot be null")
    @Size(min = 3, max = 30, message = "User name must be between 3 and 30 characters")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }
}
