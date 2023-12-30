package org.example.controller.routinemanagercontroller.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class GetAllRoutinesResponse {
    HashMap<String, String> routines;
}
