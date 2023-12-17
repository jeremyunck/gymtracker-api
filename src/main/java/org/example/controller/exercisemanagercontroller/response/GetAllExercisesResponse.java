package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class GetAllExercisesResponse {
    ArrayList<LightweightExerciseResponse> exercises;
}
