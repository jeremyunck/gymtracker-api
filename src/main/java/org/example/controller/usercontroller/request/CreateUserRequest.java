package org.example.controller.usercontroller.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
}
