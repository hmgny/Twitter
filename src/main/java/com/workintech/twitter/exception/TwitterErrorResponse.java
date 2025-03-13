package com.workintech.twitter.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TwitterErrorResponse {
    private String message;
    private int status;
    private Long timestamp;

    public TwitterErrorResponse(String message, int status, Long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
