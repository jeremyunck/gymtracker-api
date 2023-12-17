package org.example.controller.usercontroller;

import org.example.controller.usercontroller.request.CreateUserRequest;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public void createUser(CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }
}
