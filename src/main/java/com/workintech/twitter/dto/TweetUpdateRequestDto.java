package com.workintech.twitter.dto;

import lombok.Data;

public class TweetUpdateRequestDto {
    private String tweetText;
    private String media;

    public String getTweetText() {
        return tweetText;
    }

    public String getMedia() {
        return media;
    }
}