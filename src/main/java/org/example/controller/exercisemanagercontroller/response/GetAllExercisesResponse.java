package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Builder
public class GetAllExercisesResponse {
    private HashMap<String, String> exercises;
}
