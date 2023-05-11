package com.venly.task.common.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
@NoArgsConstructor
public class PathNotFoundException extends RuntimeException{
    public PathNotFoundException(String message) {
        super(message);
    }
}
