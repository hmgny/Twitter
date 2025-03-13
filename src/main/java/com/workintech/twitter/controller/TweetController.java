package com.workintech.twitter.controller;

import com.workintech.twitter.dto.*;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;

    }

    @PostMapping("/user")
    public ResponseEntity<TweetResponseDto> saveTweet(@Validated @RequestBody TweetRequestDto tweetRequestDto){
        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetRequestDto.getTweetText());
        tweet.setUser(tweetRequestDto.getUser());
        tweet.setMedia(tweetRequestDto.getMedia());
        tweet = tweetService.saveTweet(tweet);
        UserResponseDto userResponseDTO = new UserResponseDto(tweet.getUser().getUserName());

        return new ResponseEntity<>(new TweetResponseDto(tweet.getTweetText(),userResponseDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<TweetResponseDto>> findAllUserById(@Validated @RequestParam Long userId){
        List<Tweet> tweets = tweetService.findAllUserById(userId);
        List<TweetResponseDto> tweetResponseDto = tweets
                .stream()
                .map(tweet-> new TweetResponseDto(tweet.getTweetText(),new UserResponseDto("Merve")))
                .toList();
                return ResponseEntity.ok(tweetResponseDto);
    }

    @GetMapping("/findById")
    public ResponseEntity<TweetDetailResponseDto> getTweetDetail(@Validated @RequestParam Long id){
        Optional<Tweet> tweetOptional = tweetService.getTweetDetail(id);
        if(tweetOptional.isPresent()){
            Tweet tweet = tweetOptional.get();
            UserResponseDto user = new UserResponseDto(tweet.getUser().getUserName());
            List<CommentResponseDto> comments = tweet.getComments()
                    .stream()
                    .map(comment -> new CommentResponseDto(comment.getCommentText(), new UserResponseDto(comment.getUser().getUserName())))
                    .toList();
            List<LikeResponseDto> likes = tweet.getLikes()
                    .stream()
                    .map(like -> new LikeResponseDto(new UserResponseDto(like.getUser().getUserName())))
                    .toList();
            List<RetweetResponseDto> retweets = tweet.getRetweets()
                    .stream()
                    .map(retweet -> new RetweetResponseDto(retweet.getRetweetText(),new UserResponseDto(retweet.getUser().getUserName())))
                    .toList();
            TweetDetailResponseDto tweetDetailResponseDto = new TweetDetailResponseDto(
                    tweet.getTweetText(),
                    tweet.getMedia(),
                    tweet.getCreatedTime(),
                    user,
                    comments,
                    likes,
                    retweets
            );
            return ResponseEntity.ok(tweetDetailResponseDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Tweet> update(@PathVariable("id") Long id, @RequestBody Tweet updateTweet){
        Optional<Tweet> updatedTweet = tweetService.update(id, updateTweet);
        if(updatedTweet.isPresent()){
            return ResponseEntity.ok(updatedTweet.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Long id){
        tweetService.delete(id);
    }


}
