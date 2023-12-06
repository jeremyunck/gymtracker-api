package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class HealthController {
    @GetMapping("/health")
    public String health() {
        return "health";
    }

}
