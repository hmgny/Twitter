package com.workintech.twitter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TwitterException extends RuntimeException {
    private final HttpStatus httpStatus;

    public TwitterException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {

        return httpStatus;
    }
}
