package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetExerciseResponse {
    private String id;
    private String name;
    private String description;
    private String createdBy;
    private String createdAt;
    private int usedCount;
}
