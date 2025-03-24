package com.workintech.twitter.service;

import com.workintech.twitter.entity.*;
import com.workintech.twitter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final CommentRepository commentRepository;
    private final RetweetRepository retweetRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository, RetweetRepository retweetRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
        this.retweetRepository = retweetRepository;
    }

    private Like updateLikeStatus(Long userId, Long itemId, String itemType, boolean liked) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("kullanıcı bulunamadı."));
        Like like = new Like();
        like.setUser(user);

        Optional<Like> existingLike = Optional.empty();

        switch (itemType) {
            case "tweet":
                Tweet tweet = tweetRepository.findById(itemId).orElseThrow(() -> new RuntimeException("tweet bulunamadı."));
                like.setTweet(tweet);
                like.setLikedTweet(liked);
                existingLike = likeRepository.findByUser_IdAndTweet_Id(userId, itemId);
                break;
            case "comment":
                Comment comment = commentRepository.findById(itemId).orElseThrow(() -> new RuntimeException("comment bulunamadı."));
                like.setComment(comment);
                like.setLikedComment(liked);
                existingLike = likeRepository.findByUser_IdAndComment_Id(userId, itemId);
                break;
            case "retweet":
                Retweet retweet = retweetRepository.findById(itemId).orElseThrow(() -> new RuntimeException("retweet bulunamadı."));
                like.setRetweet(retweet);
                like.setLikedRetweet(liked);
                existingLike = likeRepository.findByUser_IdAndRetweet_Id(userId, itemId);
                break;
            default:
                throw new IllegalArgumentException("Geçersiz item tipi.");
        }

        if (existingLike.isPresent()) {
            like.setId(existingLike.get().getId());
        }

        return likeRepository.save(like);
    }

    @Override
    public Like likeTweet(Long userId, Long tweetId) {
        return updateLikeStatus(userId, tweetId, "tweet", true);
    }

    @Override
    public Like dislikeTweet(Long userId, Long tweetId) {
        return updateLikeStatus(userId, tweetId, "tweet", false);
    }

    @Override
    public Like likeComment(Long userId, Long commentId) {
        return updateLikeStatus(userId, commentId, "comment", true);
    }

    @Override
    public Like dislikeComment(Long userId, Long commentId) {
        return updateLikeStatus(userId, commentId, "comment", false);
    }

    @Override
    public Like likeRetweet(Long userId, Long retweetId) {
        return updateLikeStatus(userId, retweetId, "retweet", true);
    }

    @Override
    public Like dislikeRetweet(Long userId, Long retweetId) {
        return updateLikeStatus(userId, retweetId, "retweet", false);
    }
}