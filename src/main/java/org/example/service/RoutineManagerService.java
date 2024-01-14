package org.example.service;

import jakarta.transaction.Transactional;
import org.example.controller.routinemanagercontroller.request.CreateRoutineRequest;
import org.example.controller.routinemanagercontroller.response.GetAllRoutinesResponse;
import org.example.controller.routinemanagercontroller.response.GetFullRoutineByIdResponse;
import org.example.exception.InvalidRequestException;
import org.example.jpa.Exercise;
import org.example.jpa.Routine;
import org.example.jpa.RoutineExerciseMap;
import org.example.jpa.User;
import org.example.repository.ExerciseRepository;
import org.example.repository.RoutineExerciseMapRepository;
import org.example.repository.RoutineRepository;
import org.example.repository.UserRepository;
import org.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoutineManagerService {
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private RoutineExerciseMapRepository routineExerciseMapRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createRoutine(CreateRoutineRequest createRoutineRequest) {
        if (createRoutineRequest.getName() == null || createRoutineRequest.getName().isEmpty()) {
            throw new InvalidRequestException("Name cannot be null or empty");
        }
        User user = userRepository.findById(createRoutineRequest.getCreatorId()).orElseThrow(() -> new InvalidRequestException("User not found"));
        Routine routine = routineRepository.save(Routine.builder()
                .name(createRoutineRequest.getName())
                .description(createRoutineRequest.getDescription())
                .creator(user)
                .createdAt(CommonUtil.getCurrentTimestamp())
                .build());

        long[] exerciseIds = createRoutineRequest.getExerciseIds();
        for (int i = 0; i < exerciseIds.length; i++) {
            Exercise exercise = exerciseRepository.findById(exerciseIds[i]).orElseThrow(() -> new InvalidRequestException("Exercise not found"));
            routineExerciseMapRepository.save(org.example.jpa.RoutineExerciseMap.builder()
                    .routine(routine)
                    .exercise(exercise)
                    .orderNumber(i)
                    .createdAt(CommonUtil.getCurrentTimestamp())
                    .build());
        }
    }

    public GetFullRoutineByIdResponse getRoutineById(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new InvalidRequestException("Routine not found"));
        HashMap<String, String> exercises = new HashMap<>();
        routineExerciseMapRepository.findExerciseByRoutineId(id).forEach(exercise -> exercises.put(String.valueOf(exercise.getExercise().getId()), exercise.getExercise().getName()));
        return GetFullRoutineByIdResponse.builder()
                .id(String.valueOf(routine.getId()))
                .name(routine.getName())
                .description(routine.getDescription())
                .exercises(exercises)
                .build();
    }

    public GetAllRoutinesResponse getAllRoutines() {
        HashMap<String, String> routineMap = new HashMap<>();
        routineRepository.findAll().forEach(routine -> routineMap.put(String.valueOf(routine.getId()), routine.getName()));
        return GetAllRoutinesResponse.builder()
                .routines(routineMap)
                .build();
    }

    @Transactional
    public void deleteRoutine(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new InvalidRequestException("Routine not found"));
        List<RoutineExerciseMap> routineExerciseMaps = routineExerciseMapRepository.findByRoutineId(id);
        routineExerciseMaps.forEach(routineExerciseMap -> routineExerciseMapRepository.delete(routineExerciseMap));
        routineRepository.delete(routine);
    }
}
