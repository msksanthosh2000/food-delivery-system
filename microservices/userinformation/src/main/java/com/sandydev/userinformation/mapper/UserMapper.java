package com.sandydev.userinformation.mapper;

import com.sandydev.userinformation.dto.UserDTO;
import com.sandydev.userinformation.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapUserToUserDTO(User user);

    User mapUserDTOToUser(UserDTO userDTO);
}
