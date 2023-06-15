package com.challenge.viceri.api.impl;

import com.challenge.viceri.entities.UserDTO;
import com.challenge.viceri.entities.UserLoginDTO;
import com.challenge.viceri.entities.UserResponseDTO;
import com.challenge.viceri.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private static final long ID = 1L;
    private static final String NAME = "lari";
    private static final String EMAIL = "lari@email";
    private static final String PASSWORD = "1234";

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    UserResponseDTO userResponseDTO;
    UserLoginDTO userLoginDTO;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeAttributes();
    }

    @Test
    void shouldReturn200OkWhenSuccess() {
        when(userService.findAll())
                .thenReturn(List.of(userResponseDTO));

        var response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturn204NoContentWhenNull() {
        when(userService.findAll())
                .thenReturn(null);

        var response = userController.getAllUsers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturn201CreatedWhenSaveUser() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("/api");
        request.setServletPath("/users");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(userService.saveUser(userDTO)).thenReturn(userResponseDTO);

        var actualResponse = userController.saveUser(userDTO);

        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        URI expectedUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userResponseDTO.id()).toUri();
        assertEquals(expectedUri, actualResponse.getHeaders().getLocation());
    }

    @Test
    void shouldBe200OkWhenSuccess() {
        when(userService.existentUser(userLoginDTO))
              .thenReturn(true);

        var response = userController.login(userLoginDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldBe404NotFoundWhenReturnFalse() {
        when(userService.existentUser(userLoginDTO))
                .thenReturn(false);

        var response = userController.login(userLoginDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    private void initializeAttributes() {
        userResponseDTO = new UserResponseDTO(ID, NAME, EMAIL);
        userDTO = new UserDTO(NAME, EMAIL, PASSWORD);
        userLoginDTO = new UserLoginDTO(EMAIL, PASSWORD);
    }
}