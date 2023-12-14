package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Exercise already exists")
public class ExerciseAlreadyExistsException extends RuntimeException {
}
