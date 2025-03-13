package com.workintech.twitter.service;

import com.workintech.twitter.entity.Retweet;

public interface RetweetService {

    public Retweet tweetRetweet(Long userId, Long tweetId);
    public void deleteRetweet(Long id);
}
