package com.challenge.viceri.services;

import com.challenge.viceri.entities.User;
import com.challenge.viceri.entities.UserResponseDTO;
import com.challenge.viceri.mappers.UserMapper;
import com.challenge.viceri.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.getAllUsers();
        return users.stream()
                .map(mapper::toUserResponse).toList();
    }
}
