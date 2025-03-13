package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.repository.CommentRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Comment addComment(Long userId, Long tweetId, String commentText) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("Kullanıcı bulunamadı"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("Tweet bulunamadı"));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setTweet(tweet);
        comment.setCommentText(commentText);
        comment.setCreatedTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Long userId, Long tweetId, String commentText) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("comment bulunamadı"));

        if (!comment.getUser().getId().equals(userId) || !comment.getTweet().getId().equals(tweetId)) {
            throw new RuntimeException("Bu yorumu güncelleyemezsiniz!");
        }
        comment.setCommentText(commentText);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId, Long tweetId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("comment bulunamadı"));

        if (!comment.getUser().getId().equals(userId) || !comment.getTweet().getId().equals(tweetId)) {
            throw new RuntimeException("Bu yorumu silemezsiniz!");
        }
        commentRepository.delete(comment);
    }
}
