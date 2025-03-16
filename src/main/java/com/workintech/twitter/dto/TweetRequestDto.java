package com.workintech.twitter.dto;

import com.workintech.twitter.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TweetRequestDto {

    @NotBlank(message = "Tweet text cannot be empty")
    @NotNull(message = "Tweet text cannot be null")
    private String tweetText;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    private User user;
    private String media;

    public TweetRequestDto(){}

    public TweetRequestDto(String tweetText, Long userId, User user, String media) {
        this.tweetText = tweetText;
        this.userId = userId;
        this.user = user;
        this.media = media;
    }

    public String getTweetText() {
        return tweetText;
    }

    public User getUser() {
        return user;
    }

    public String getMedia() {
        return media;
    }

    public Long getUserId() {
        return userId;
    }
}
