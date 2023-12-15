package org.example.controller.workoutmanagercontroller.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExerciseLogEntry {
    private long exerciseId;
    private ArrayList<ExerciseSet> exerciseSets;
}
