package org.example.controller.workoutmanagercontroller;

import org.example.controller.workoutmanagercontroller.request.CreateWorkoutLogEntryRequest;
import org.example.service.WorkoutManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workoutManagerController")
public class WorkoutManagerController {
    @Autowired
    private WorkoutManagerService workoutManagerService;

    @PostMapping("/createWorkoutLogEntry")
    public void createWorkoutLogEntry(@RequestBody CreateWorkoutLogEntryRequest createWorkoutLogEntryRequest) {
        workoutManagerService.createWorkoutLogEntry(createWorkoutLogEntryRequest);
    }
}
