package org.example.controller.exercisemanagercontroller;

import org.example.controller.exercisemanagercontroller.request.CreateExerciseRequest;
import org.example.controller.exercisemanagercontroller.response.GetExerciseResponse;
import org.example.service.ExerciseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/exerciseManagerController")
public class ExerciseManagerController {
    @Autowired
    private ExerciseManagerService exerciseManagerService;

    @PostMapping("/createExercise")
    public void createExercise(CreateExerciseRequest createExerciseRequest) {
        exerciseManagerService.createExercise(createExerciseRequest);
    }

    @GetMapping("/getExercise")
    public ResponseEntity<GetExerciseResponse> getExercise(long id) {
        return ResponseEntity.ok(exerciseManagerService.getExercise(id));
    }

}
