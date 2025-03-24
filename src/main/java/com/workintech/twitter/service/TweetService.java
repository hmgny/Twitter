package com.workintech.twitter.service;

import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    public List<Tweet> getAllTweets();
    public Tweet saveTweet(Tweet tweet);
    public List<Tweet> findAllUserById(Long userId);
    public Optional<Tweet> getTweetDetail(Long id);
    public Optional<Tweet> update(Long id, Tweet updateTweet);
    public void delete(Long id);
}
