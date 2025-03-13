package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comment;


public interface CommentService {
    public Comment addComment(Long userId, Long tweetId, String commentText);
    public Comment updateComment(Long commentId, Long userId, Long tweetId, String commentText);
    public void deleteComment(Long commentId, Long userId, Long tweetId);
}
