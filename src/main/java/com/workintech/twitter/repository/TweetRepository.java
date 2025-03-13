package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUser(User user);
}
