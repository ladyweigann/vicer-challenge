package com.challenge.viceri.api.impl;

import com.challenge.viceri.api.UserApi;
import com.challenge.viceri.entities.UserResponseDTO;
import com.challenge.viceri.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController implements UserApi {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAll();

        return users.stream()
                .findFirst()
                .map(user -> ResponseEntity.ok(users))
                .orElse(ResponseEntity.noContent().build());
    }
}
