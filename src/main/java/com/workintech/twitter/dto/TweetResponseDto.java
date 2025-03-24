package com.workintech.twitter.dto;


public record TweetResponseDto(String tweetText, String username, Long userId, int likeCount, int retweetCount) {
}
