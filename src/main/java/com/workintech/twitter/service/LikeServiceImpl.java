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

    @Override
    public Like likeTweet(Long userId, Long tweetId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("tweet bulunamadı."));
        Optional<Like> islike = likeRepository.findByUser_IdAndTweet_Id(userId, tweetId);
        if(islike.isPresent()){
            throw new RuntimeException("tweet zaten beğenilmiş.");
        }
        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        like.setLikedTweet(true);
        return likeRepository.save(like);
    }

    @Override
    public Like dislikeTweet(Long userId, Long tweetId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("tweet bulunamadı."));
        Optional<Like> isdislike = likeRepository.findByUser_IdAndTweet_Id(userId, tweetId);
        if(isdislike.isEmpty()){
            throw new RuntimeException("tweet zaten beğenilmemiş.");
        }
        Like dislike = new Like();
        dislike.setUser(user);
        dislike.setTweet(tweet);
        dislike.setLikedTweet(false);
        return likeRepository.save(dislike);
    }

    @Override
    public Like likeComment(Long userId, Long commentId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("comment bulunamadı."));
        Optional<Like> isLike = likeRepository.findByUser_IdAndComment_Id(userId, commentId);
        if(isLike.isPresent()){
            throw new RuntimeException("comment zaten beğenilmiş.");
        }
        Like like = new Like();
        like.setUser(user);
        like.setComment(comment);
        like.setLikedComment(true);
        return likeRepository.save(like);
    }

    @Override
    public Like dislikeComment(Long userId, Long commentId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new RuntimeException("comment bulunamadı."));
        Optional<Like> isdisLike = likeRepository.findByUser_IdAndComment_Id(userId, commentId);
        if(isdisLike.isEmpty()){
            throw new RuntimeException("comment zaten beğenilmemiş.");
        }
        Like dislike = new Like();
        dislike.setUser(user);
        dislike.setComment(comment);
        dislike.setLikedComment(false);
        return likeRepository.save(dislike);
    }

    @Override
    public Like likeRetweet(Long userId, Long retweetId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Retweet retweet = retweetRepository.findById(retweetId).orElseThrow(()->new RuntimeException("retweet bulunamadı."));
        Optional<Like> isLike = likeRepository.findByUser_IdAndRetweet_Id(userId, retweetId);
        if(isLike.isPresent()){
            throw new RuntimeException("retweet zaten beğenilmiş.");
        }
        Like like = new Like();
        like.setUser(user);
        like.setRetweet(retweet);
        like.setLikedRetweet(true);
        return likeRepository.save(like);
    }

    @Override
    public Like dislikeRetweet(Long userId, Long retweetId) {
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("kullanıcı bulunamadı."));
        Retweet retweet = retweetRepository.findById(retweetId).orElseThrow(()->new RuntimeException("retweet bulunamadı."));
        Optional<Like> isLike = likeRepository.findByUser_IdAndRetweet_Id(userId, retweetId);
        if(isLike.isEmpty()){
            throw new RuntimeException("retweet zaten beğenilmemiş.");
        }
        Like like = new Like();
        like.setUser(user);
        like.setRetweet(retweet);
        like.setLikedRetweet(false);
        return likeRepository.save(like);
    }
}
