package com.challenge.viceri.mappers;

import com.challenge.viceri.entities.User;
import com.challenge.viceri.entities.UserDTO;
import com.challenge.viceri.entities.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserResponseDTO toUserResponse(User user);

}
