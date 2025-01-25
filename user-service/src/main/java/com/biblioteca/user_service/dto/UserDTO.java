package com.biblioteca.user_service.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role; // Cambiado de userType a role para claridad
    private Boolean status; // true = activo, false = suspendido
    private String idInstitucional; // Agregado si es relevante para el sistema
}
