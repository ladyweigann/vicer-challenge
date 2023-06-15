package com.challenge.viceri.api;

import com.challenge.viceri.entities.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "users", description = "TO-DO API USERS")
@RequestMapping(value = "/users", produces = {APPLICATION_JSON_VALUE})
public interface UserApi {
    @Operation(
            summary = "Get all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))
                            }),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE)
                            })
            }
    )
    @GetMapping
    ResponseEntity<List<UserResponseDTO>> getAllUsers();
}
