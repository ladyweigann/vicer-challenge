package com.challenge.viceri.services;

import com.challenge.viceri.entities.User;
import com.challenge.viceri.entities.UserDTO;
import com.challenge.viceri.entities.UserLoginDTO;
import com.challenge.viceri.entities.UserResponseDTO;
import com.challenge.viceri.mappers.UserMapper;
import com.challenge.viceri.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private static final long ID = 1L;
    private static final String NAME = "lari";
    private static final String EMAIL = "lari@email";
    private static final String PASSWORD = "1234";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    User user;
    UserResponseDTO userResponseDTO;
    UserDTO userDTO;
    UserLoginDTO userLoginDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeAttributes();
    }

    @Test
    void shouldReturnAListOfUsersWhenSuccess() {
        when(userRepository.getAllUsers())
            .thenReturn(List.of(user));

        when(mapper.toUserResponse(any())).thenReturn(userResponseDTO);

        var response = userService.findAll();

        verify(userRepository, times(1)).getAllUsers();
        assertEquals(UserResponseDTO.class, response.get(0).getClass());
    }

    @Test
    void shoudSaveUserWhenSuccess() {
        when(userRepository.createNewUser(any(), any(), any()))
                .thenReturn(user);

        when(mapper.toUserResponse(any())).thenReturn(userResponseDTO);

        var response = userService.saveUser(userDTO);

        verify(userRepository, times(1)).createNewUser(any(), any(), any());
        assertEquals(UserResponseDTO.class, response.getClass());
    }

    @Test
    void shouldReturnTrueWhenSuccess() {
        when(userRepository.existsByEmail(any())).thenReturn(user);

        var response = userService.existentUser(userLoginDTO);

        verify(userRepository, times(1)).existsByEmail(any());
        assertTrue(response);
    }

    @Test
    void shouldReturnFalseWhenNotFoundMatch() {
        when(userRepository.existsByEmail(any()))
                .thenReturn(null);

        var response = userService.existentUser(userLoginDTO);

        verify(userRepository, times(1)).existsByEmail(any());
        assertFalse(response);
    }

    private void initializeAttributes() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userResponseDTO = new UserResponseDTO(ID, NAME, EMAIL);
        userDTO = new UserDTO(NAME, EMAIL, PASSWORD);
        userLoginDTO = new UserLoginDTO(EMAIL, PASSWORD);
    }
}