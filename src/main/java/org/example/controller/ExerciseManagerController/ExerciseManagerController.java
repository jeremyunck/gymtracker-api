package org.example.controller.ExerciseManagerController;

import org.example.controller.ExerciseManagerController.request.CreateExerciseRequest;
import org.example.service.ExerciseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}
