package com.challenge.viceri.api.impl;

import com.challenge.viceri.api.UserApi;
import com.challenge.viceri.entities.UserDTO;
import com.challenge.viceri.entities.UserLoginDTO;
import com.challenge.viceri.entities.UserResponseDTO;
import com.challenge.viceri.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = userService.findAll();
        if(users != null){
            return ResponseEntity.ok(users);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> saveUser(UserDTO userDTO) {
        UserResponseDTO newUser = userService.saveUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.id()).toUri();

        return ResponseEntity.created(uri).body(newUser);
    }

    @Override
    public ResponseEntity<UserResponseDTO> login(UserLoginDTO loginDTO) {
        boolean exists = userService.existentUser(loginDTO);

        if(exists) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
