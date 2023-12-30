package org.example.controller.exercisemanagercontroller.request;

import lombok.Data;

@Data
public class CreateExerciseRequest {
    private long creatorId;
    private String name;
    private String description;
}
