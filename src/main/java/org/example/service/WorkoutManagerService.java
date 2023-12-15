package org.example.service;

import org.example.controller.workoutmanagercontroller.request.CreateWorkoutLogEntryRequest;
import org.example.controller.workoutmanagercontroller.request.ExerciseLogEntry;
import org.example.controller.workoutmanagercontroller.request.ExerciseSet;
import org.example.jpa.*;
import org.example.repository.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WorkoutManagerService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseSetLogEntryRepository exerciseSetLogEntryRepository;
    @Autowired
    private WorkoutLogEntryRepository workoutLogEntryRepository;

    public void createWorkoutLogEntry(CreateWorkoutLogEntryRequest createWorkoutLogEntryRequest) {
        // Save workout log entry
        User user = userRepository.findById(createWorkoutLogEntryRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Routine routine = routineRepository.findById(createWorkoutLogEntryRequest.getRoutineId()).orElse(null);
        WorkoutLogEntry workoutLogEntry = workoutLogEntryRepository.save(WorkoutLogEntry.builder()
                .user(user)
                .routine(routine)
                .build());
        // Use the saved workout log entry id to save the exercise set log entries
        for (ExerciseLogEntry exerciseLogEntry : createWorkoutLogEntryRequest.getExerciseLogEntries()) {
            Exercise exercise = exerciseRepository.findById(exerciseLogEntry.getExerciseId()).orElseThrow(() -> new RuntimeException("Exercise not found"));
            // Iterate through the exercise sets and save them
            for (int i = 0; i < exerciseLogEntry.getExerciseSets().size(); i++) {
                ExerciseSet exerciseSet = exerciseLogEntry.getExerciseSets().get(i);
                exerciseSetLogEntryRepository.save(ExerciseSetLogEntry.builder()
                        .user(user)
                        .workoutLogEntry(workoutLogEntry)
                        .exercise(exercise)
                        .setNumber(i)
                        .weight(exerciseSet.getWeight())
                        .repetitions(exerciseSet.getRepetitions())
                        .failed(exerciseSet.isFailed())
                        .createdAt(Timestamp.valueOf(DateTime.now().toString()))
                        .build());
            }

        }

    }

}
