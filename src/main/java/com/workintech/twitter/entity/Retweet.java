package com.workintech.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "retweet", schema = "twitter")
public class Retweet {

    public Retweet(){}

    public Retweet(Long id, Boolean isRetweeted, LocalDateTime createdTime, String retweetText, String media, User user, Tweet tweet, List<Comment> comments, List<Like> likes) {
        this.id = id;
        this.isRetweeted = isRetweeted;
        this.createdTime = createdTime;
        this.retweetText = retweetText;
        this.media = media;
        this.user = user;
        this.tweet = tweet;
        this.comments = comments;
        this.likes = likes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_retweeted")
    private Boolean isRetweeted;

    @Column(name = "created_time")
    @NotNull(message = "Created time cannot be null")
    private LocalDateTime createdTime;

    @Column(name = "retweet_text")
    @NotNull(message = "Retweet text cannot be null")
    @NotBlank(message = "Retweet text cannot be empty")
    private String retweetText;

    @Column(name = "media")
    private String media;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "original_tweet_id", nullable = false)
    private Tweet tweet;

    @OneToMany(mappedBy = "retweet", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "retweet", cascade = CascadeType.ALL)
    private List<Like> likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRetweeted() { return isRetweeted; }

    public void setRetweeted(Boolean retweeted) { isRetweeted = retweeted; }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getRetweetText() {
        return retweetText;
    }

    public void setRetweetText(String retweetText) {
        this.retweetText = retweetText;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}

