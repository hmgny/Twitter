package com.workintech.twitter.service;

import com.workintech.twitter.entity.Like;

public interface LikeService {
    public Like likeTweet(Long userId, Long tweetId);
    public Like dislikeTweet(Long userId, Long tweetId);

    public Like likeComment(Long userId, Long commentId);
    public Like dislikeComment(Long userId, Long commentId);

    public Like likeRetweet(Long userId, Long retweetId);
    public Like dislikeRetweet(Long userId, Long retweetId);
}
