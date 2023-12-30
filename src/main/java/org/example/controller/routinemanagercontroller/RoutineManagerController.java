package org.example.controller.routinemanagercontroller;

import org.example.controller.routinemanagercontroller.request.CreateRoutineRequest;
import org.example.controller.routinemanagercontroller.response.GetAllRoutinesResponse;
import org.example.controller.routinemanagercontroller.response.GetFullRoutineByIdResponse;
import org.example.service.RoutineManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routineManager")
public class RoutineManagerController {
    @Autowired
    private RoutineManagerService routineManagerService;

    @PostMapping("/createRoutine")
    public void createRoutine(@RequestBody CreateRoutineRequest createRoutineRequest) {
        routineManagerService.createRoutine(createRoutineRequest);
    }

    @GetMapping("/getRoutineById/{id}")
    public GetFullRoutineByIdResponse getRoutineById(@PathVariable("id") long id) {
        return routineManagerService.getRoutineById(id);
    }

    @GetMapping("/getAllRoutines")
    public GetAllRoutinesResponse getAllRoutines() {
        return routineManagerService.getAllRoutines();
    }

    @DeleteMapping("/deleteRoutine/{id}")
    public void deleteRoutine(@PathVariable("id") long id) {
        routineManagerService.deleteRoutine(id);
    }
}
