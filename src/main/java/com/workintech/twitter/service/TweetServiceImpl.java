package com.workintech.twitter.service;

import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        if(tweet.getUser() == null){
            throw new RuntimeException("Tweet'in bir sahibi olmalı!");
        }
     return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
           throw new RuntimeException("Kullanıcı bulunamadı");
        }
        User user=userOptional.get();
        return tweetRepository.findByUser(user);
    }

    @Override
    public Optional<Tweet> getTweetDetail(Long id) {
        return tweetRepository.findById(id);
    }

    @Override
    public Optional<Tweet> update(Long id, Tweet updateTweet) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if(!optionalTweet.isPresent()){
            throw new RuntimeException("Böyle bir tweet bulunamadı");
        }
        Tweet tweet = optionalTweet.get();
        tweet.setTweetText(updateTweet.getTweetText());
        tweet.setUser(updateTweet.getUser());
        tweet.setMedia(updateTweet.getMedia());
        tweet.setCreatedTime(updateTweet.getCreatedTime());
        tweet.setComments(updateTweet.getComments());
        tweet.setLikes(updateTweet.getLikes());
        tweet.setRetweets(updateTweet.getRetweets());

        Tweet updatedTweet = tweetRepository.save(tweet);

        return Optional.of(updatedTweet);
    }

    @Override
    public void delete(Long id) {
        Optional<Tweet> tweet = tweetRepository.findById(id);
        Optional<User> user = userRepository.findById(id);
        if(tweet.isPresent() && user.isPresent()){
            tweetRepository.deleteById(id);
        }
    }
}
