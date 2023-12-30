package org.example.controller.exercisemanagercontroller;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.exercisemanagercontroller.request.CreateExerciseRequest;
import org.example.controller.exercisemanagercontroller.response.GetAllExercisesResponse;
import org.example.controller.exercisemanagercontroller.response.GetExerciseByIdResponse;
import org.example.controller.exercisemanagercontroller.response.SearchExerciseByNameResponse;
import org.example.service.ExerciseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/exerciseManager")
@CrossOrigin("*")
public class ExerciseManagerController {
    @Autowired
    private ExerciseManagerService exerciseManagerService;

    @PostMapping("/createExercise")
    public void createExercise(@RequestBody CreateExerciseRequest createExerciseRequest) {
        log.info("Received request to create exercise");
        exerciseManagerService.createExercise(createExerciseRequest);
    }

    @GetMapping("/getExercise/{id}")
    public ResponseEntity<GetExerciseByIdResponse> getExercise(@PathVariable("id") long id) {
        log.info("Received request to get exercise with id: " + id);
        return ResponseEntity.ok(exerciseManagerService.getExerciseById(id));
    }

    @GetMapping("/getAllExercises")
    public ResponseEntity<GetAllExercisesResponse> getAllExercises() {
        log.info("Received request to get all exercises");
        return ResponseEntity.ok(exerciseManagerService.getAllExercises());
    }

    @GetMapping("/searchExerciseByName/{name}")
    public ResponseEntity<SearchExerciseByNameResponse> searchExerciseByName(@PathVariable("name") String name) {
        log.info("Received request to search exercises by name: " + name);
        return ResponseEntity.ok(exerciseManagerService.searchExerciseByName(name));
    }

    @DeleteMapping("/deleteExercise/{id}")
    public void deleteExercise(@PathVariable("id") long id) {
        log.info("Received request to delete exercise with id: " + id);
        exerciseManagerService.deleteExercise(id);
    }
}
