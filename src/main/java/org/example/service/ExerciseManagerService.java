package org.example.service;

import org.example.controller.ExerciseManagerController.request.CreateExerciseRequest;
import org.example.controller.ExerciseManagerController.response.GetExerciseResponse;
import org.example.exception.ExerciseFailedToCreateException;
import org.example.exception.ExerciseInvalidRequest;
import org.example.model.Exercise;
import org.example.repository.ExerciseManagerRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ExerciseManagerService {
    @Autowired
    private ExerciseManagerRepository exerciseManagerRepository;

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
                .createdAt(new Timestamp(DateTime.now().getMillis()))
                .usedCount(0)
                .build();

        exerciseManagerRepository.save(exercise);

        if (exerciseManagerRepository.findById(exercise.getId()).isEmpty()) {
            throw new ExerciseFailedToCreateException("Failed to create exercise");
        }
    }

public GetExerciseResponse getExercise(long id) {
        Exercise exercise = exerciseManagerRepository.findById(id).orElseThrow(() -> new ExerciseInvalidRequest("Exercise not found"));
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
