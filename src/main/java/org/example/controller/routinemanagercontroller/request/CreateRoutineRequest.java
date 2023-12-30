package org.example.controller.routinemanagercontroller.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateRoutineRequest {
    private long creatorId;
    private String name;
    private String description;
    private long[] exerciseIds;
}
