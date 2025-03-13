package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUser_IdAndTweet_Id(Long userId, Long tweetId);
    Optional<Like> findByUser_IdAndComment_Id(Long userId, Long commentId);
    Optional<Like> findByUser_IdAndRetweet_Id(Long userId, Long retweetId);
}
