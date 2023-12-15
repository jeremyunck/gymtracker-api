package org.example.controller.workoutmanagercontroller.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateWorkoutLogEntryRequest {
    private long userId;
    private long routineId;
    private ArrayList<ExerciseLogEntry> exerciseLogEntries;
}
