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

    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
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
