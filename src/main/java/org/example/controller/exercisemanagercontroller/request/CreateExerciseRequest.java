package org.example.controller.exercisemanagercontroller.request;

import lombok.Data;

@Data
public class CreateExerciseRequest {
    private String name;
    private String description;
    private String createdBy;
}
