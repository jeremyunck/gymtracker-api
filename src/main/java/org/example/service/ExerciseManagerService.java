package org.example.service;

import org.example.controller.exercisemanagercontroller.request.CreateExerciseRequest;
import org.example.controller.exercisemanagercontroller.response.GetExerciseResponse;
import org.example.exception.ExerciseFailedToCreateException;
import org.example.exception.ExerciseInvalidRequest;
import org.example.jpa.Exercise;
import org.example.repository.ExerciseRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ExerciseManagerService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public void createExercise(CreateExerciseRequest createExerciseRequest) {
        if (createExerciseRequest.getName() == null || createExerciseRequest.getName().isEmpty()) {
            throw new ExerciseInvalidRequest("Name must be non-empty");
        }
        if (createExerciseRequest.getDescription() == null || createExerciseRequest.getDescription().isEmpty()) {
            throw new ExerciseInvalidRequest("Description must be non-empty");
        }
        if (createExerciseRequest.getCreatedBy() == null || createExerciseRequest.getCreatedBy().isEmpty()) {
            throw new ExerciseInvalidRequest("CreatedBy must be non-empty");
        }

        Exercise exercise = Exercise.builder()
                .name(createExerciseRequest.getName())
                .description(createExerciseRequest.getDescription())
                .createdBy(createExerciseRequest.getCreatedBy())
                .createdAt(Timestamp.valueOf(DateTime.now().toString()))
                .usedCount(0)
                .build();

        exerciseRepository.save(exercise);

        if (exerciseRepository.findById(exercise.getId()).isEmpty()) {
            throw new ExerciseFailedToCreateException("Failed to create exercise");
        }
    }

public GetExerciseResponse getExercise(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new ExerciseInvalidRequest("Exercise not found"));
        return GetExerciseResponse.builder()
                .id(String.valueOf(exercise.getId()))
                .name(exercise.getName())
                .description(exercise.getDescription())
                .createdBy(exercise.getCreatedBy())
                .createdAt(exercise.getCreatedAt().toString())
                .usedCount(exercise.getUsedCount())
                .build();
    }

}
