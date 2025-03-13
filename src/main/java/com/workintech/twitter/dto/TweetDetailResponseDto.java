package com.workintech.twitter.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TweetDetailResponseDto(String tweetText, String media, LocalDateTime createdTime, UserResponseDto user, List<CommentResponseDto> comment, List<LikeResponseDto> like, List<RetweetResponseDto> retweet) {
}
