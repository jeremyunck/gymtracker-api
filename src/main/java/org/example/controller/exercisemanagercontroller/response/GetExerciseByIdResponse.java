package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetExerciseByIdResponse {
    LightweightExerciseResponse exercise;
}
