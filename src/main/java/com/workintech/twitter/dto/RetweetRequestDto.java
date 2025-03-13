package com.workintech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class RetweetRequestDto {

    public RetweetRequestDto(){}

    public RetweetRequestDto(String retweetText, String media) {
        this.retweetText = retweetText;
        this.media = media;
    }

    @NotNull(message = "Retweet text cannot be null")
    @NotBlank(message = "Retweet text cannot be empty")
    private String retweetText;

    private String media;

    public String getRetweetText() {
        return retweetText;
    }

    public String getMedia() {
        return media;
    }
}
