package com.workintech.twitter.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "likes", schema = "twitter")
public class Like {

    public Like(){}

    public Like(Long id, Boolean likedTweet, Boolean likedComment, Boolean likedRetweet, User user, Tweet tweet, Retweet retweet, Comment comment) {
        this.id = id;
        this.likedTweet = likedTweet;
        this.likedComment = likedComment;
        this.likedRetweet = likedRetweet;
        this.user = user;
        this.tweet = tweet;
        this.retweet = retweet;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "liked_tweet")
    private Boolean likedTweet;

    @Column(name = "liked_comment")
    private Boolean likedComment;

    @Column(name = "liked_retweet")
    private Boolean likedRetweet;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "retweet_id")
    private Retweet retweet;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLikedTweet() {
        return likedTweet;
    }

    public void setLikedTweet(Boolean likedTweet) {
        this.likedTweet = likedTweet;
    }

    public Boolean getLikedComment() {
        return likedComment;
    }

    public void setLikedComment(Boolean likedComment) {
        this.likedComment = likedComment;
    }

    public Boolean getLikedRetweet() {
        return likedRetweet;
    }

    public void setLikedRetweet(Boolean likedRetweet) {
        this.likedRetweet = likedRetweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Retweet getRetweet() {
        return retweet;
    }

    public void setRetweet(Retweet retweet) {
        this.retweet = retweet;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
