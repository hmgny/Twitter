package com.workintech.twitter.controller;

import com.workintech.twitter.dto.*;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.service.TweetService;
import com.workintech.twitter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;

    @Autowired
    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<TweetResponseDto> saveTweet(@Validated @RequestBody TweetRequestDto tweetRequestDto){
        Optional<User> userOptional = userService.findById(tweetRequestDto.getUserId());
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Tweet tweet = new Tweet();
        tweet.setTweetText(tweetRequestDto.getTweetText());
        tweet.setUser(userOptional.get());
        tweet.setMedia(tweetRequestDto.getMedia());
        tweet.setCreatedTime(LocalDateTime.now());
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
    public ResponseEntity<TweetResponseDto> update(@PathVariable("id") Long id, @RequestBody TweetUpdateRequestDto updateTweetDto) {
        Optional<Tweet> optionalTweet = tweetService.getTweetDetail(id);
        if (!optionalTweet.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Tweet tweet = optionalTweet.get();
        if (updateTweetDto.getTweetText() != null) {
            tweet.setTweetText(updateTweetDto.getTweetText());
        }
        if (updateTweetDto.getMedia() != null) {
            tweet.setMedia(updateTweetDto.getMedia());
        }

        tweetService.saveTweet(tweet);
        UserResponseDto userResponseDTO = new UserResponseDto(tweet.getUser().getUserName());

        return new ResponseEntity<>(new TweetResponseDto(tweet.getTweetText(), userResponseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Long id){
        tweetService.delete(id);
    }


}
