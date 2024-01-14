package org.example.controller.workoutmanagercontroller.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@Data
public class CreateWorkoutLogEntryRequest {
    private long userId;
    private long routineId;
    private Map<Long, ArrayList<ExerciseSet>> exerciseLogEntries;
}
