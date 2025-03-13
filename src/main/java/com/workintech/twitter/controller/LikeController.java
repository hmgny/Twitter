package com.workintech.twitter.controller;

import com.workintech.twitter.entity.Like;

import com.workintech.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/tweet/like")
    public ResponseEntity<Like> likeTweet(@RequestParam Long userId, @RequestParam Long tweetId){
        Like like = likeService.likeTweet(userId,tweetId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/tweet/dislike/{tweetId}")
    public ResponseEntity<Like> dislikeTweet(@RequestParam Long userId, @PathVariable("tweetId") Long tweetId){
        Like like = likeService.dislikeTweet(userId,tweetId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/comment/like")
    public ResponseEntity<Like> likeComment(@RequestParam Long userId, @RequestParam Long commentId){
        Like like = likeService.likeComment(userId,commentId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("comment/dislike/{commentId}")
    public ResponseEntity<Like> dislikeComment(@RequestParam Long userId, @PathVariable("commentId") Long commentId){
        Like like = likeService.dislikeComment(userId,commentId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/retweet/like")
    public ResponseEntity<Like> likeRetweet(@RequestParam Long userId, @RequestParam Long retweetId){
        Like like = likeService.likeRetweet(userId,retweetId);
        return ResponseEntity.ok(like);
    }

    @PostMapping("retweet/dislike/{retweetId}")
    public ResponseEntity<Like> dislikeRetweet(@RequestParam Long userId, @PathVariable("retweetId") Long retweetId){
        Like like = likeService.dislikeRetweet(userId,retweetId);
        return ResponseEntity.ok(like);
    }


}
