package org.example.controller.routinemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class GetFullRoutineByIdResponse {
    private String id;
    private String name;
    private String description;
    private HashMap<String, String> exercises;
}
