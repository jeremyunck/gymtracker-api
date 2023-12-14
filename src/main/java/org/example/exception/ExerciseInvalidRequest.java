package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request")
public class ExerciseInvalidRequest extends RuntimeException {
    public ExerciseInvalidRequest(String message) {
        super(message);
    }
}
