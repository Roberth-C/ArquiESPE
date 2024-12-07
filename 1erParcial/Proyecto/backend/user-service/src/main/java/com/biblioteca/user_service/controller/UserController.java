package com.biblioteca.user_service.controller;

import com.biblioteca.user_service.dto.UserDTO;
import com.biblioteca.user_service.entity.User;
import com.biblioteca.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id).map(this::convertToDTO);
    }

    // Crear un nuevo usuario
    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        return convertToDTO(userService.createUser(user));
    }

    // Suspender usuario
    @PutMapping("/{id}/suspend")
    public void suspendUser(@PathVariable Long id) {
        userService.suspendUser(id);
    }

    // Activar usuario
    @PutMapping("/{id}/activate")
    public void activateUser(@PathVariable Long id) {
        userService.activateUser(id);
    }

    // Convertir entidad a DTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setUserType(user.getUserType().name());
        dto.setStatus(user.getStatus());
        return dto;
    }
}
