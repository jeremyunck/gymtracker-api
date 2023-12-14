package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exercise failed to create")
public class ExerciseFailedToCreateException extends RuntimeException {
    public ExerciseFailedToCreateException(String message) {
        super(message);
    }
}
