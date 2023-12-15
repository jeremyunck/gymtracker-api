package org.example.controller.workoutmanagercontroller.request;

import lombok.Data;

@Data
public class ExerciseSet {
    private int weight;
    private int repetitions;
    private boolean failed;
}
