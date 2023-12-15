package org.example.service;

import org.example.controller.usercontroller.request.CreateUserRequest;
import org.example.jpa.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(CreateUserRequest createUserRequest) {
        userRepository.save(User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .build());
    }
}
