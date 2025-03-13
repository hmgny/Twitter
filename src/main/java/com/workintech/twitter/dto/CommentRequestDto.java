package com.workintech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CommentRequestDto {
    @NotNull(message = "Comment text cannot be null")
    @NotBlank(message = "Comment text cannot be empty")
    private String commentText;

    public CommentRequestDto(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }
}
