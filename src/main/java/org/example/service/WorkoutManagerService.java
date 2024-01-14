package org.example.service;

import jakarta.transaction.Transactional;
import org.example.controller.workoutmanagercontroller.request.CreateWorkoutLogEntryRequest;
import org.example.controller.workoutmanagercontroller.request.ExerciseSet;
import org.example.jpa.*;
import org.example.repository.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

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

    @Transactional
    public void createWorkoutLogEntry(CreateWorkoutLogEntryRequest createWorkoutLogEntryRequest) {
        // Save workout log entry
        User user = userRepository.findById(createWorkoutLogEntryRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found for id: " + createWorkoutLogEntryRequest.getUserId()));
        Routine routine = routineRepository.findById(createWorkoutLogEntryRequest.getRoutineId()).orElse(null);
        WorkoutLogEntry workoutLogEntry = workoutLogEntryRepository.save(WorkoutLogEntry.builder()
                .user(user)
                .routine(routine)
                .createdAt(new Timestamp(DateTime.now().getMillis()))
                .build());
        // Use the saved workout log entry id to save the exercise set log entries
        Map<Long, ArrayList<ExerciseSet>> exerciseSets = createWorkoutLogEntryRequest.getExerciseLogEntries();
        for (Map.Entry<Long, ArrayList<ExerciseSet>> entry : exerciseSets.entrySet()) {
            Exercise exercise = exerciseRepository.findById(entry.getKey()).orElseThrow(() -> new RuntimeException("Exercise not found for id: " + entry.getKey()));
            for (int i = 0; i < entry.getValue().size(); i++) {
                ExerciseSet exerciseSet = entry.getValue().get(i);
                exerciseSetLogEntryRepository.save(ExerciseSetLogEntry.builder()
                        .workoutLogEntry(workoutLogEntry)
                        .exercise(exercise)
                        .setNumber(i)
                        .weight(exerciseSet.getWeight())
                        .repetitions(exerciseSet.getRepetitions())
                        .createdAt(new Timestamp(DateTime.now().getMillis()))
                        .build());
            }
        }

    }

}
