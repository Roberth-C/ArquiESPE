// Ejemplo de UserMapper.java
package com.biblioteca.util;

import com.biblioteca.user_service.dto.UserDTO;
import com.biblioteca.user_service.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().toString());
        dto.setStatus(user.getStatus());
        dto.setIdInstitucional(user.getIdInstitucional());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setStatus(dto.getStatus());
        user.setIdInstitucional(dto.getIdInstitucional());
        return user;
    }
}
