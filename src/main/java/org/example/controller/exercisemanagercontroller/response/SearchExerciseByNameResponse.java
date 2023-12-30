package org.example.controller.exercisemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class SearchExerciseByNameResponse {
    private HashMap<String, String> exercises;
}
