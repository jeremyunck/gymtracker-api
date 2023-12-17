package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.exercisemanagercontroller.request.CreateExerciseRequest;
import org.example.controller.exercisemanagercontroller.response.LightweightExerciseResponse;
import org.example.controller.exercisemanagercontroller.response.GetAllExercisesResponse;
import org.example.controller.exercisemanagercontroller.response.GetExerciseByIdResponse;
import org.example.controller.exercisemanagercontroller.response.SearchExerciseByNameResponse;
import org.example.exception.ExerciseInvalidRequest;
import org.example.jpa.Exercise;
import org.example.jpa.ExerciseView;
import org.example.jpa.User;
import org.example.repository.ExerciseRepository;
import org.example.repository.UserRepository;
import org.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
            throw new ExerciseInvalidRequest("Name must be non-empty");
        }
        if (createExerciseRequest.getCreatorId() <= 0) {
            log.error("Creator ID invalid: " + createExerciseRequest.getCreatorId());
            throw new ExerciseInvalidRequest("Creator ID invalid: " + createExerciseRequest.getCreatorId());
        }
        if (exerciseRepository.findByName(createExerciseRequest.getName()).isPresent()) {
            log.error("Exercise with name " + createExerciseRequest.getName() + " already exists");
            throw new ExerciseInvalidRequest("Exercise with name " + createExerciseRequest.getName() + " already exists");
        }
        log.info("Creating exercise with name " + createExerciseRequest.getName());
        User user = userRepository.findById(createExerciseRequest.getCreatorId()).orElseThrow(() -> new ExerciseInvalidRequest("User not found"));

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
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new ExerciseInvalidRequest("Exercise not found"));
        return GetExerciseByIdResponse.builder()
                .exercise(convertExerciseToResponse(exercise))
                .build();
    }

    public GetAllExercisesResponse getAllExercises() {
        ArrayList<LightweightExerciseResponse> exercises = new ArrayList<>();
        for (Exercise exercise : exerciseRepository.findAll()) {
            exercises.add(convertExerciseToResponse(exercise));
        }
        return GetAllExercisesResponse.builder()
                .exercises(exercises)
                .build();
    }

    public SearchExerciseByNameResponse searchExerciseByName(String name) {
        ArrayList<LightweightExerciseResponse> exercises = new ArrayList<>();
        for (ExerciseView exerciseView : exerciseRepository.findExerciseByNameLike("%" + name + "%")) {
            exercises.add(convertExerciseViewToResponse(exerciseView));
        }
        return SearchExerciseByNameResponse.builder()
                .exercises(exercises)
                .build();
    }

    private LightweightExerciseResponse convertExerciseToResponse(Exercise exercise) {
        return LightweightExerciseResponse.builder()
                .id(String.valueOf(exercise.getId()))
                .name(exercise.getName())
                .build();
    }

    private LightweightExerciseResponse convertExerciseViewToResponse(ExerciseView exerciseView) {
        return LightweightExerciseResponse.builder()
                .id(String.valueOf(exerciseView.getId()))
                .name(exerciseView.getName())
                .build();
    }

}
