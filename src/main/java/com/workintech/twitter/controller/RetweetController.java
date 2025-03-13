package com.workintech.twitter.controller;

import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public ResponseEntity<Retweet> tweetRetweet(@RequestParam Long userId, @RequestParam Long tweetId){
        Retweet retweet = retweetService.tweetRetweet(userId, tweetId);
        return ResponseEntity.ok(retweet);
    }

    @DeleteMapping("/{id}")
    public void deleteRetweet(@PathVariable("id") Long id){
        retweetService.deleteRetweet(id);
    }


}
