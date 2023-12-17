package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;
import org.example.jpa.ExerciseView;

@Data
@Builder
public class LightweightExerciseResponse {
    private String id;
    private String name;
}
