package org.example.controller.ExerciseManagerController.request;

import lombok.Data;

@Data
public class CreateExerciseRequest {
    private String name;
    private String description;
    private String createdBy;
}
