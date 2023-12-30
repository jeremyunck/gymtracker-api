package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.exercisemanagercontroller.request.CreateExerciseRequest;
import org.example.controller.exercisemanagercontroller.response.GetAllExercisesResponse;
import org.example.controller.exercisemanagercontroller.response.GetExerciseByIdResponse;
import org.example.controller.exercisemanagercontroller.response.SearchExerciseByNameResponse;
import org.example.exception.InvalidRequestException;
import org.example.jpa.Exercise;
import org.example.jpa.User;
import org.example.repository.ExerciseRepository;
import org.example.repository.UserRepository;
import org.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ExerciseManagerService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;

    public void createExercise(CreateExerciseRequest createExerciseRequest) {
        if (createExerciseRequest.getName() == null || createExerciseRequest.getName().isEmpty()) {
            log.error("Name must be non-empty");
            throw new InvalidRequestException("Name must be non-empty");
        }
        if (createExerciseRequest.getCreatorId() <= 0) {
            log.error("Creator ID invalid: " + createExerciseRequest.getCreatorId());
            throw new InvalidRequestException("Creator ID invalid: " + createExerciseRequest.getCreatorId());
        }
        if (exerciseRepository.findByName(createExerciseRequest.getName()).isPresent()) {
            log.error("Exercise with name " + createExerciseRequest.getName() + " already exists");
            throw new InvalidRequestException("Exercise with name " + createExerciseRequest.getName() + " already exists");
        }
        log.info("Creating exercise with name " + createExerciseRequest.getName());
        User user = userRepository.findById(createExerciseRequest.getCreatorId()).orElseThrow(() -> new InvalidRequestException("User not found"));

        Exercise exercise = Exercise.builder()
                .name(createExerciseRequest.getName())
                .description(createExerciseRequest.getDescription())
                .creator(user)
                .createdAt(CommonUtil.getCurrentTimestamp())
                .usedCount(0)
                .build();

        exerciseRepository.save(exercise);
    }

    public GetExerciseByIdResponse getExerciseById(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new InvalidRequestException("Exercise not found"));
        return GetExerciseByIdResponse.builder()
                .id(String.valueOf(exercise.getId()))
                .name(exercise.getName())
                .description(exercise.getDescription())
                .build();
    }

    public GetAllExercisesResponse getAllExercises() {
        HashMap<String, String> exercises = new HashMap<>();
        exerciseRepository.findAll().forEach(exercise -> exercises.put(String.valueOf(exercise.getId()), exercise.getName()));
        return GetAllExercisesResponse.builder()
                .exercises(exercises)
                .build();
    }

    public SearchExerciseByNameResponse searchExerciseByName(String name) {
        HashMap<String, String> exercises = new HashMap<>();
        exerciseRepository.findExerciseByNameLike("%" + name + "%").forEach(exercise -> exercises.put(String.valueOf(exercise.getId()), exercise.getName()));
        return SearchExerciseByNameResponse.builder()
                .exercises(exercises)
                .build();
    }

    public void deleteExercise(long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new InvalidRequestException("Exercise not found"));
        exerciseRepository.delete(exercise);
    }
}
