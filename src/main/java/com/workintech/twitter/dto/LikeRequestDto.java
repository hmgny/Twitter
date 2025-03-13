package com.workintech.twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class LikeRequestDto {
    public LikeRequestDto(){}

    public LikeRequestDto(Long tweetId, Long retweetId, Long commentId) {
        this.tweetId = tweetId;
        this.retweetId = retweetId;
        this.commentId = commentId;
    }

    private Long tweetId;
    private Long retweetId;
    private Long commentId;

    public Long getTweetId() {
        return tweetId;
    }

    public Long getRetweetId() {
        return retweetId;
    }

    public Long getCommentId() {
        return commentId;
    }
}
