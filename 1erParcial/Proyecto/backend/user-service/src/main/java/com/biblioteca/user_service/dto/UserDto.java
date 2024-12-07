package com.biblioteca.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String userType; // Ejemplo: ADMIN, STUDENT, TEACHER
    private Boolean status; // true = activo, false = suspendido
}
