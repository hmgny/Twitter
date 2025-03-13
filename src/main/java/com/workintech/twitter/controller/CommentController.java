package com.workintech.twitter.controller;

import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@Validated @RequestParam Long userId, @RequestParam Long tweetId, @RequestBody String commentText){
        Comment comment = commentService.addComment(userId, tweetId, commentText);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@Validated @PathVariable("commentId") Long commentId, @RequestParam Long userId, @RequestParam Long tweetId, @RequestBody String commentText){
        Comment comment = commentService.updateComment(commentId,userId,tweetId,commentText);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@Validated @PathVariable("commentId") Long commentId, @RequestParam Long userId, @RequestParam Long tweetId){
        commentService.deleteComment(commentId, userId, tweetId);
    }


}
